package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.GeodesicMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.GeodeticMeasurementEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement.ResponseGeodesicMeasurementDto;

import java.util.List;

public interface GeodesicMeasurementService {

    List<ResponseGeodesicMeasurementDto> save(GeodeticMeasurementEquipmentDto measurementsDto);

    List<ResponseGeodesicMeasurementDto> update(List<GeodesicMeasurementDto> measurementsDto);

    List<ResponseGeodesicMeasurementDto> getAll(Long id);

    void delete(Long id);
}