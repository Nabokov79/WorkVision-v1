package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;

import java.util.List;

public interface DocumentationTemplateService {

    void saveWithSubsectionTemplate(SubsectionTemplate template
                                  , List<DocumentationTemplateDto> documentations);

    List<ResponseDocumentationTemplateDto> getAll();

   void delete(Long id);
}