package ru.nabokovsg.laboratoryNK.repository.measurement.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;

import java.util.Set;

public interface CompletedRepairElementRepository extends JpaRepository<CompletedRepairElement, Long> {

    Set<CompletedRepairElement> findAllBySurveyJournalId(Long surveyJournalId);

    Set<CompletedRepairElement> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}