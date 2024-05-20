package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.gm.PointDifferenceMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ControlPoint;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicPointType;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.PointDifference;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;
import ru.nabokovsg.laboratoryNK.repository.measurement.gm.PointDifferenceRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PointDifferenceServiceImpl implements PointDifferenceService {

    private final PointDifferenceRepository repository;
    private final PointDifferenceMapper mapper;
    private final CalculationGeodesicMeasurementService calculationService;

    @Override
    public void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, Set<ControlPoint> controlPoints) {
        repository.saveAll(create(acceptableDeviationsGeodesy, controlPoints)
                  .stream()
                  .toList());
    }
    @Override
    public void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, Set<ControlPoint> controlPoints) {
        List<Long> pointDifferencesIds = controlPoints.stream().map(ControlPoint::getEquipmentId).toList();
        Map<Long, Long> ids = new HashMap<>();
        controlPoints.forEach(g -> ids.put(g.getSurveyJournalId(), g.getEquipmentId()));
        Long surveyJournalId = ids.keySet().stream().toList().get(0);
        Map<Integer, PointDifference> pointDifferences = repository.findAllBySurveyJournalIdAndEquipmentId(
                                                                                              surveyJournalId
                                                                                            , ids.get(surveyJournalId))
                                               .stream()
                                               .collect(Collectors.toMap(PointDifference::getFirstPlaceNumber, p -> p));
        if (!pointDifferences.isEmpty()) {
            repository.saveAll(create(acceptableDeviationsGeodesy, controlPoints)
                      .stream()
                      .map(p -> mapper.mapToUpdatePointDifference(pointDifferences.get(p.getFirstPlaceNumber()), p))
                      .toList());
            return;
        }
        throw new NotFoundException(
                String.format("PointDifference equipment Id=%s not found for update", pointDifferencesIds));
    }

    @Override
    public Set<PointDifference> getAll(Long surveyJournalId, Long equipmentId) {
        Set<PointDifference> pointDifferences = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId
                                                                                                , equipmentId);
        if (pointDifferences.isEmpty()) {
            throw new NotFoundException(
                    String.format("PointDifference by surveyJournalId=%s and equipmentId=%s not found", surveyJournalId
                            , equipmentId));
        }
        return pointDifferences;
    }

    private List<PointDifference> create(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                                       , Set<ControlPoint> controlPoints) {
        Map<Integer, ControlPoint> points = controlPoints.stream()
                                                       .collect(Collectors.toMap(ControlPoint::getPlaceNumber, c -> c));
        return Stream.of(calculatedNeighboringPoints(acceptableDeviationsGeodesy, points)
                       , calculatedDiametricalPoints(acceptableDeviationsGeodesy, points))
                     .flatMap(Collection::stream)
                     .toList();
    }

    private List<PointDifference> calculatedNeighboringPoints(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                                                            , Map<Integer, ControlPoint> points) {
        Map<Integer, Integer> neighboringPoints = points.keySet()
                                                        .stream()
                                                        .collect(Collectors.toMap(p -> p
                                                                        , p -> getNeighboringPoints(p, points.size())));
        return neighboringPoints.keySet()
                                .stream()
                                .map(p -> mapper.mapToPointDifference(GeodesicPointType.NEIGHBORING
                                                , points.get(p)
                                                , p
                                                , points.get(neighboringPoints.get(p)).getPlaceNumber()
                                                , Math.abs(calculationService.getDeviation(
                                                                  points.get(p).getDeviation()
                                                                , points.get(neighboringPoints.get(p)).getDeviation())))
                                )
                                .map(p -> determinePermissibleDeviation(p, acceptableDeviationsGeodesy))
                                .toList();
    }

    private Integer getNeighboringPoints(Integer firstPlace, int size) {
        if (firstPlace != size) {
            return ++firstPlace;
        }
       return 1;
    }

    private List<PointDifference> calculatedDiametricalPoints(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                                                            , Map<Integer, ControlPoint> points) {
        int difference = (int) Math.floor(points.keySet().stream().max(Integer::compare).orElse(0) / 2.0);
        if (difference == 0) {
            throw new RuntimeException(
                    String.format("Error in calculating the diametric points, difference=%s", difference)
            );
        }
        Map<Integer, Integer> diametricalPoints = points.keySet().stream()
                                                                .filter(i -> i <= difference)
                                                                .collect(Collectors.toMap(i -> i, i -> i + difference));
        return diametricalPoints.keySet()
                                .stream()
                                .map(p -> mapper.mapToPointDifference(GeodesicPointType.DIAMETRICAL
                                        , points.get(p)
                                        , p
                                        , points.get(diametricalPoints.get(p)).getPlaceNumber()
                                        , Math.abs(calculationService.getDeviation(
                                                                  points.get(p).getDeviation()
                                                                , points.get(diametricalPoints.get(p)).getDeviation())))
                                )
                .map(p -> determinePermissibleDeviation(p, acceptableDeviationsGeodesy))
                                .toList();
    }

    private PointDifference determinePermissibleDeviation(PointDifference pointDifference
                                                         , AcceptableDeviationsGeodesy acceptableDeviationsGeodesy) {
        switch (pointDifference.getGeodesicPointType()) {
            case DIAMETRICAL -> {
                return mapper.mapPointDifferenceWithControlPointMeasurement(pointDifference
                        , (pointDifference.getDifference() - acceptableDeviationsGeodesy.getMeasurementError())
                                                      > acceptableDeviationsGeodesy.getMaxDifferenceDiametricPoints());
            }
            case NEIGHBORING -> {
                return mapper.mapPointDifferenceWithControlPointMeasurement(pointDifference,
                        (pointDifference.getDifference() - acceptableDeviationsGeodesy.getMeasurementError())
                                                     > acceptableDeviationsGeodesy.getMaxDifferenceNeighboringPoints());
            }
            default -> {
                return pointDifference;
            }
        }
    }
}