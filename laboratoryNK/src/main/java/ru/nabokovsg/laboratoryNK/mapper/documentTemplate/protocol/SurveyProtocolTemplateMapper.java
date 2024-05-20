package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.protocol;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ShortResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.SurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SurveyProtocolTemplateMapper {

    @Mapping(source = "protocolDto.documentTypeId", target = "documentTypeId")
    @Mapping(source = "protocolDto.equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "documentHeaders", target = "leftHeaderTemplates")
    @Mapping(source = "documentType.title", target = "title")
    @Mapping(source = "documentType.subtitle", target = "subtitle")
    @Mapping(source = "conclusionTemplate", target = "conclusionTemplate")
    @Mapping(source = "protocolDto.id", target = "id")
    SurveyProtocolTemplate mapToProtocolTemplate(SurveyProtocolTemplateDto protocolDto
                                         , DiagnosticDocumentType documentType
                                         , Set<DocumentHeaderTemplate> documentHeaders
                                         , ConclusionTemplate conclusionTemplate);

    ShortResponseSurveyProtocolTemplateDto mapToShortResponseProtocolTemplateDto(SurveyProtocolTemplate protocolTemplate);

    ResponseSurveyProtocolTemplateDto mapToResponseProtocolTemplateDto(SurveyProtocolTemplate protocolTemplate);
}