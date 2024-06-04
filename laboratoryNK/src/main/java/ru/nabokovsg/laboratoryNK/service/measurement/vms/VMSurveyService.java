package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VMSurvey;

import java.util.Set;

public interface VMSurveyService {

    VMSurvey save(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId);

    VMSurvey saveWithVisualInspection(VisualInspectionDto inspectionDto);

    Set<VMSurvey> getAll(Long surveyJournalId, Long equipmentId);
}