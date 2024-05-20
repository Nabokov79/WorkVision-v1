package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;

import java.util.List;

public interface ResultCalculationsMeasurementsGeodesyService {

    void save(Long equipmentId, List<GeodesicMeasurement> measurements);

    void update(Long equipmentId, List<GeodesicMeasurement> measurements);

}