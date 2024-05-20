package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface DocumentationTemplateMapper {

    @Mapping(source = "documentation.id", target = "documentationId")
    @Mapping(source = "documentName", target = "documentName")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "subsectionTemplate", ignore = true)
    @Mapping(target = "id", ignore = true)
    DocumentationTemplate mapToRegulatoryDocumentationTemplate(Documentation documentation
                                                                       , String documentName);

    @Mapping(source = "documentation.id", target = "documentationId")
    @Mapping(source = "documentName", target = "documentName")
    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.subsectionTemplate", target = "subsectionTemplate")
    @Mapping(source = "template.id", target = "id")
    DocumentationTemplate mapToUpdateRegulatoryDocumentationTemplate(DocumentationTemplate template
                                                                             , Documentation documentation
                                                                             , String documentName);

    ResponseDocumentationTemplateDto mapToResponseRegulatoryDocumentationTemplateDto(
                                                                              DocumentationTemplate template);

    @Mapping(source = "template.documentationId", target = "documentationId")
    @Mapping(source = "template.documentName", target = "documentName")
    @Mapping(source = "sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "subsectionTemplate", target = "subsectionTemplate")
    @Mapping(source = "template.id", target = "id")
    DocumentationTemplate mapToWithSubsectionTemplate(DocumentationTemplate template
                                                              , SubsectionTemplate subsectionTemplate
                                                              , Integer sequentialNumber);
}