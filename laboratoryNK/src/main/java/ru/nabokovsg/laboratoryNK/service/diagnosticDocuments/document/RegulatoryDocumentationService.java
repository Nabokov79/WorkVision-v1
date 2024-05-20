package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;

import java.util.List;

public interface RegulatoryDocumentationService {

    void save(Subsection subsection, List<DocumentationTemplate> documentationTemplate);
}