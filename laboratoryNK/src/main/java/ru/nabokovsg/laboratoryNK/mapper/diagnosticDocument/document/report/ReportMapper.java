package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    Report mapToReport(Long surveyJournalId,  Long equipmentDiagnosedId, PageTitle pageTitle);
}