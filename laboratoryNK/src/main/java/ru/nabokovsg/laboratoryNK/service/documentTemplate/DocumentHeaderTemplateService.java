package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.DocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;

import java.util.List;
import java.util.Set;

public interface DocumentHeaderTemplateService {

    ResponseDocumentHeaderTemplateDto save(DocumentHeaderTemplateDto headerDto);

    ResponseDocumentHeaderTemplateDto update(DocumentHeaderTemplateDto headerDto);

    List<ResponseDocumentHeaderTemplateDto> getAll(Long id);

    Set<DocumentHeaderTemplate> getAllByDocumentTypeId(Long documentTypeId);

    void delete(Long id);
}