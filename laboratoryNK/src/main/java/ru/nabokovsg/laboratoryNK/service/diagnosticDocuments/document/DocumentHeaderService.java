package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;

import java.util.Set;

public interface DocumentHeaderService {

    void saveForPageTitle(PageTitle pageTitle, Set<DocumentHeaderTemplate> documentHeaders);
}