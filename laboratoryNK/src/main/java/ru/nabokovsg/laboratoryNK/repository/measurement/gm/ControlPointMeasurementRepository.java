package ru.nabokovsg.laboratoryNK.repository.measurement.gm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.ControlPoint;

import java.util.Set;

public interface ControlPointMeasurementRepository extends JpaRepository<ControlPoint, Long> {

    Set<ControlPoint> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}