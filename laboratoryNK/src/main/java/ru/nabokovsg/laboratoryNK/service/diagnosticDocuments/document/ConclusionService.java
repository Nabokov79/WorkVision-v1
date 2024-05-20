package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Conclusion;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;

public interface ConclusionService {

    Conclusion save(ConclusionTemplate template);
}