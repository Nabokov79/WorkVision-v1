package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;

public interface PageTitleService {

    PageTitle save(DiagnosticDocument document
                 , String building
                 , LaboratoryEmployee chief
                 , PageTitleTemplate pageTitleTemplate);
}