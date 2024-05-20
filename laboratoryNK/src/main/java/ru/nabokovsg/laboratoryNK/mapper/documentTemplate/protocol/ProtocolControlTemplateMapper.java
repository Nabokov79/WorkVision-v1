package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.protocol;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ShortResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProtocolControlTemplateMapper {

    @Mapping(source = "protocolDto.documentTypeId", target = "documentTypeId")
    @Mapping(source = "documentHeaders", target = "leftHeaderTemplates")
    @Mapping(source = "documentType.title", target = "title")
    @Mapping(source = "documentType.subtitle", target = "subtitle")
    @Mapping(source = "protocolDto.id", target = "id")
    ProtocolControlTemplate mapToProtocolTemplate(ProtocolControlTemplateDto protocolDto
                                                , DiagnosticDocumentType documentType
                                                , Set<DocumentHeaderTemplate> documentHeaders);

    ShortResponseProtocolControlTemplateDto mapToShortResponseProtocolTemplateDto(ProtocolControlTemplate protocolTemplate);

    ResponseProtocolControlTemplateDto mapToResponseProtocolTemplateDto(ProtocolControlTemplate protocolTemplate);
}