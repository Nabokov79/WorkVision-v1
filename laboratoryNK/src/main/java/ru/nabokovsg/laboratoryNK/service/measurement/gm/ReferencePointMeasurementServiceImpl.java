package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.gm.ReferencePointMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;
import ru.nabokovsg.laboratoryNK.repository.measurement.gm.ReferencePointRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferencePointMeasurementServiceImpl implements ReferencePointMeasurementService {

    private final ReferencePointRepository repository;
    private final ReferencePointMapper mapper;
    private final DeviationYearService deviationYearService;
    private final CalculationGeodesicMeasurementService calculationService;

    @Override
    public void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, List<GeodesicMeasurement> measurements) {
        Integer min = calculationService.getMinMeasurement(measurements.stream()
                                                                       .map(GeodesicMeasurement::getReferencePointValue)
                                                                       .toList());
        deviationYearService.save(repository.saveAll(
                measurements.stream()
                        .filter(m -> m.getReferencePointValue() != null)
                        .map(mapper::mapToReferencePoint)
                        .map(r -> getReferencePoint(acceptableDeviationsGeodesy, r, min))
                        .toList()));
    }

    @Override
    public void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                     , List<GeodesicMeasurement> measurements) {
        Integer min = calculationService.getMinMeasurement(measurements.stream()
                                                                       .map(GeodesicMeasurement::getReferencePointValue)
                                                                       .toList());
        Map<Long, Long> ids = new HashMap<>();
        measurements.forEach(g -> ids.put(g.getSurveyJournalId(), g.getEquipmentId()));
        Long surveyJournalId = ids.keySet().stream().toList().get(0);
        Map<Integer, ReferencePoint> points = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId
                                                                                            , ids.get(surveyJournalId))
                                                     .stream()
                                                     .collect(Collectors.toMap(ReferencePoint::getPlaceNumber, p -> p));
        deviationYearService.save(repository.saveAll(
                measurements.stream()
                        .map(m -> mapper.mapToUpdateReferencePoint(points.get(m.getNumberMeasurementLocation()), m))
                        .map(r -> getReferencePoint(acceptableDeviationsGeodesy, r, min))
                        .toList()));
    }

    @Override
    public Set<ReferencePoint> getAll(Long surveyJournalId, Long equipmentId) {
        Set<ReferencePoint> points = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (points.isEmpty()) {
            throw new NotFoundException(
                    String.format("ReferencePoint by surveyJournalId=%s and equipmentId=%s not found", surveyJournalId
                            , equipmentId));
        }
        return points;
    }

    private ReferencePoint getReferencePoint(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                                            , ReferencePoint point
                                            , Integer min) {
        Integer deviation = calculationService.getDeviation(min, point.getCalculatedHeight());
        Integer precipitation = calculationService.getPrecipitation(deviation, point.getDeviationYeas());
        return mapper.mapWithReferencePointData(point
                                             , deviation
                                             , precipitation
                                             , comparePrecipitation(precipitation
                                                           , acceptableDeviationsGeodesy.getAcceptablePrecipitation()));
    }

    private boolean comparePrecipitation(Integer precipitation, Integer acceptablePrecipitation) {
        if (acceptablePrecipitation != null) {
            return precipitation > acceptablePrecipitation;
        }
        return false;
    }
}