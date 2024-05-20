package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;

import java.util.List;

public interface RecalculateGeodesyMeasurementsService {

    List<GeodesicMeasurement> recalculateByTransition(List<GeodesicMeasurement> geodesicMeasurements);
}