package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.ControlPoint;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.PointDifference;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;

import java.util.Set;

public interface PointDifferenceService {

    void save(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, Set<ControlPoint> controlPoints);

    void update(AcceptableDeviationsGeodesy acceptableDeviationsGeodesy, Set<ControlPoint> controlPoints);

    Set<PointDifference> getAll(Long surveyJournalId, Long equipmentId);
}