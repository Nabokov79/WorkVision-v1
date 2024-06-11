package ru.nabokovsg.laboratoryNK.mapper.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;

@Mapper(componentModel = "spring")
public interface EquipmentSurveyMapper {

    @Mapping(source = "surveyJournalId", target = "surveyJournalId")
    @Mapping(source = "equipmentId", target = "equipmentId")
    @Mapping(source = "element.id", target = "elementId")
    @Mapping(source = "element.elementName", target = "elementName")
    EquipmentSurvey mapToVMSurvey(Long surveyJournalId, Long equipmentId, EquipmentElement element);

    @Mapping(source = "partElement.id", target = "partElementId")
    @Mapping(source = "partElement.partName", target = "partName")
    EquipmentSurvey mapWithPartElement(@MappingTarget EquipmentSurvey vmSurvey, PartElement partElement);

    @Mapping(source = "inspection", target = "inspection")
    EquipmentSurvey mapWithVisualInspection(@MappingTarget EquipmentSurvey vmSurvey, String inspection);
}