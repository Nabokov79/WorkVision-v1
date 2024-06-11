package ru.nabokovsg.laboratoryNK.repository.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;

import java.util.Set;

public interface EquipmentSurveyRepository extends JpaRepository<EquipmentSurvey, Long> {

    Set<EquipmentSurvey> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}