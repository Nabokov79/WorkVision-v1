package ru.nabokovsg.laboratoryNK.mapper.common;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.documentation.DocumentationDto;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;

@Mapper(componentModel = "spring")
public interface DocumentationMapper {

    Documentation mapToDocumentation(DocumentationDto documentationDto);

    DocumentationDto mapToDocumentationDto(Documentation documentation);
}