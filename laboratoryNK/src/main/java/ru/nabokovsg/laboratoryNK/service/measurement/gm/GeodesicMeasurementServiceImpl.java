package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.GeodesicMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.ResponseGeodesicMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.gm.GeodesicMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;
import ru.nabokovsg.laboratoryNK.repository.measurement.gm.GeodesicMeasurementRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeodesicMeasurementServiceImpl implements GeodesicMeasurementService {

    private final GeodesicMeasurementRepository repository;
    private final GeodesicMeasurementMapper mapper;
    private final ResultCalculationsMeasurementsGeodesyService resultCalculationsMeasurementsGeodesyService;

    @Override
    public List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto) {
        Map<Integer, GeodesicMeasurement> measurements = repository.findAllBySurveyJournalIdAndEquipmentId(
                                                                                    measurementsDto.getSurveyJournalId()
                                                                                  , measurementsDto.getEquipmentId())
                                .stream()
                                .collect(Collectors.toMap(GeodesicMeasurement::getNumberMeasurementLocation, g -> g));
       if (!measurements.isEmpty()) {
           measurementsDto.setMeasurements(measurementsDto.getMeasurements()
                   .stream()
                   .filter(m -> !measurements.containsKey(m.getNumberMeasurementLocation()))
                   .toList());
       }
        if (!measurementsDto.getMeasurements().isEmpty()) {
            repository.saveAll(measurementsDto.getMeasurements()
                            .stream()
                            .map(m -> mapper.mapToGeodesicMeasurement(m
                                    , measurementsDto.getEquipmentId()
                                    , measurementsDto.getSurveyJournalId()))
                            .toList())
                    .forEach(m -> measurements.put(m.getNumberMeasurementLocation(), m));
            resultCalculationsMeasurementsGeodesyService.save(measurementsDto.getEquipmentId()
                                                            , new ArrayList<>(measurements.values()));
        }
        return measurements.values()
                .stream()
                .map(mapper::mapToResponseGeodesicMeasurementDto)
                .toList();
    }


    @Override
    public List<ResponseGeodesicMeasurementDto> update(List<GeodesicMeasurementDto> measurementsDto) {
        List<GeodesicMeasurement> measurements = repository.findAllById(measurementsDto.stream()
                .map(GeodesicMeasurementDto::getId)
                .toList());
        Map<Long, Long> ids = new HashMap<>();
        measurements.forEach(g -> ids.put(g.getSurveyJournalId(), g.getEquipmentId()));
        if (measurementsDto.size() == measurements.size()) {
            Long surveyJournalId = ids.keySet().stream().toList().get(0);
            measurements = repository.saveAll(measurementsDto
                            .stream()
                            .map(m -> mapper.mapToGeodesicMeasurement(m, ids.get(surveyJournalId), surveyJournalId))
                            .toList());
            resultCalculationsMeasurementsGeodesyService.update(ids.get(surveyJournalId), measurements);
            return measurements
                    .stream()
                    .map(mapper::mapToResponseGeodesicMeasurementDto)
                    .toList();
        }
        throw new NotFoundException(
                String.format("Geodetic measurement equipment by ids=%s not found for update", ids));
    }

    @Override
    public List<ResponseGeodesicMeasurementDto> getAll(Long id) {
        return repository.findAllByEquipmentId(id)
                .stream()
                .map(mapper::mapToResponseGeodesicMeasurementDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("GeodesicMeasurement with id=%s not found for delete", id));
    }
}