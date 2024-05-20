package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;

public interface ConclusionTemplateService {

    ResponseConclusionTemplateDto save(ConclusionTemplateDto conclusionDto);

    ResponseConclusionTemplateDto update(ConclusionTemplateDto conclusionDto);

    ResponseConclusionTemplateDto get(Long id);

    ConclusionTemplate getByDocumentTypeId(Long documentTypeId);

    void delete(Long id);
}