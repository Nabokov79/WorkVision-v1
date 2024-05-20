package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentHeader;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;

@Mapper(componentModel = "spring")
public interface DocumentHeaderMapper {

    DocumentHeader mapToDocumentHeader(DocumentHeaderTemplate documentHeaders);

    @Mapping(source = "pageTitle", target = "pageTitle")
    DocumentHeader mapWithPageTitle(@MappingTarget DocumentHeader documentHeaders, PageTitle pageTitle);
}
