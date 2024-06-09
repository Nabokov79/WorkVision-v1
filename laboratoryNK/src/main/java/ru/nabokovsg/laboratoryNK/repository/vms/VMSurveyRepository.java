package ru.nabokovsg.laboratoryNK.repository.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.VMSurvey;

import java.util.Set;

public interface VMSurveyRepository extends JpaRepository<VMSurvey, Long> {

    Set<VMSurvey> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}