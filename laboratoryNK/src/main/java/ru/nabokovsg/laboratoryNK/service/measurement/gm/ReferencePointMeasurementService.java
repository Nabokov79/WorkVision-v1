package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;

import java.util.List;
import java.util.Set;

public interface ReferencePointMeasurementService {

    void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, List<GeodesicMeasurement> measurements);

    void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, List<GeodesicMeasurement> measurements);

    Set<ReferencePoint> getAll(Long surveyJournalId, Long equipmentId);
}