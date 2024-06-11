package ru.nabokovsg.laboratoryNK.service.vms;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;

import java.util.Set;

public interface EquipmentSurveyService {

    EquipmentSurvey save(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId);

    EquipmentSurvey saveWithVisualInspection(VisualInspectionDto inspectionDto);

    Set<EquipmentSurvey> getAll(Long surveyJournalId, Long equipmentId);
}