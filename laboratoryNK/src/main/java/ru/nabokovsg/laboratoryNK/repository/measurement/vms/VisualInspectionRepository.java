package ru.nabokovsg.laboratoryNK.repository.measurement.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VisualInspection;

import java.util.Set;

public interface VisualInspectionRepository extends JpaRepository<VisualInspection, Long> {

    Set<VisualInspection> findAllBySurveyJournalId(Long surveyJournalId);

    Set<VisualInspection> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}