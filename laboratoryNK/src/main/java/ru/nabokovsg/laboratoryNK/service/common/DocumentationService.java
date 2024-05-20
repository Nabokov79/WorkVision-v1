package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.documentation.DocumentationDto;

import java.util.List;

public interface DocumentationService {

    DocumentationDto save(DocumentationDto documentationDto);

    DocumentationDto update(DocumentationDto documentationDto);

    List<DocumentationDto> getAll(List<Long> ids, String number, String title);

   void delete(Long id);
}