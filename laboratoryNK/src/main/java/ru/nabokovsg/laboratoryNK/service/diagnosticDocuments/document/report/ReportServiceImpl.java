package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report.ReportMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.report.ReportRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.AppendicesService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.report.ReportTemplateService;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final ReportMapper mapper;
    private final ReportTemplateService reportTemplateService;
    private final PageTitleService pageTitleService;
    private final SectionService sectionService;
    private final AppendicesService appendicesService;


    @Override
    public void save(SurveyJournal surveyJournal, DiagnosticDocument document) {
        ReportTemplate template = reportTemplateService.getByDocumentTypeIdAndEquipmentTypeId(
                                                                            document.getDiagnosticDocumentType().getId()
                                                                          , document.getEquipmentDiagnosedId());
        Report report = repository.save(mapper.mapToReport(document.getSurveyJournalId()
                                                         , document.getEquipmentDiagnosedId()
                                                         , pageTitleService.save(document
                                                                               , surveyJournal.getBuilding()
                                                                               , surveyJournal.getChief()
                                                                               , template.getPageTitleTemplate())));
       sectionService.save(report, template, surveyJournal);
       appendicesService.saveForReport(report, template.getAppendices());
    }
}