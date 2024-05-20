package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentEquipmentPassport;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;

@Mapper(componentModel = "spring")
public interface DocumentEquipmentPassportMapper {

    @Mapping(source = "header", target = "header")
    @Mapping(source = "meaning", target = "meaning")
    @Mapping(source = "section", target = "section")
    @Mapping(target = "id", ignore = true)
    DocumentEquipmentPassport mapToDocumentEquipmentPassport(String header, String meaning, Section section);
}