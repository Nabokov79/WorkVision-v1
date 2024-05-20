package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.RegulatoryDocumentation;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;

@Mapper(componentModel = "spring")
public interface RegulatoryDocumentationMapper {

    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.documentName", target = "documentName")
    @Mapping(source = "subsection", target = "subsection")
    @Mapping(target = "id", ignore = true)
    RegulatoryDocumentation mapToRegulatoryDocumentation(DocumentationTemplate template
                                                       , Subsection subsection);
}