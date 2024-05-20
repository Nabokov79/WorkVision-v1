package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.DiagnosticDocumentTypeDto;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.TypeDocument;

@Mapper(componentModel = "spring")
public interface DiagnosticDocumentTypeMapper {

    @Mapping(source = "typeDocument", target = "typeDocument")
    @Mapping(source = "documentTypeDto.id", target = "id")
    DiagnosticDocumentType mapToDiagnosticDocument(DiagnosticDocumentTypeDto documentTypeDto, TypeDocument typeDocument);

    DiagnosticDocumentTypeDto mapToDiagnosticDocumentTypeDto(DiagnosticDocumentType documentType);
}