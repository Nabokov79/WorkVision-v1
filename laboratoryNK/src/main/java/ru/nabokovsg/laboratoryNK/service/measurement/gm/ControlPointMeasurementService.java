package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.ControlPoint;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;

import java.util.List;
import java.util.Set;

public interface ControlPointMeasurementService {

    Set<ControlPoint> save(List<GeodesicMeasurement> measurements);

    Set<ControlPoint> update(List<GeodesicMeasurement> measurements);

    Set<ControlPoint> getAll(Long surveyJournalId, Long equipmentId);
}