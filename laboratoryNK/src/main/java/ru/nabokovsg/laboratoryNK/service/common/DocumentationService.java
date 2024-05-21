package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.documentation.DocumentationDto;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;

import java.util.List;

public interface DocumentationService {

    DocumentationDto save(DocumentationDto documentationDto);

    DocumentationDto update(DocumentationDto documentationDto);

    List<DocumentationDto> getAll(List<Long> ids, String number, String title);

    Documentation getById(Long id);

    List<Documentation> getAllByIds(List<Long> ids);

   void delete(Long id);
}