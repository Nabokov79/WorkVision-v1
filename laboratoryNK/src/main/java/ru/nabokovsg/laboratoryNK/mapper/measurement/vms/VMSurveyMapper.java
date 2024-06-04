package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VMSurvey;

@Mapper(componentModel = "spring")
public interface VMSurveyMapper {

    @Mapping(source = "surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "equipmentId", target = "equipmentId")
    @Mapping(source = "element.id", target = "elementId")
    @Mapping(source = "element.elementName", target = "elementName")
    VMSurvey mapToVMSurvey(Long surveyJournalId, Long equipmentId, EquipmentElement element);

    @Mapping(source = "partElement.id", target = "partElementId")
    @Mapping(source = "partElement.partName", target = "partName")
    VMSurvey mapWithPartElement(@MappingTarget VMSurvey vmSurvey, PartElement partElement);

    @Mapping(source = "inspection", target = "inspection")
    VMSurvey mapWithVisualInspection(@MappingTarget VMSurvey vmSurvey, String inspection);
}