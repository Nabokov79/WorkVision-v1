package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report.ProtocolReportMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.report.ProtocolReportRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.SubsectionService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.TableService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.ConclusionService;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProtocolReportServiceImpl implements ProtocolReportService {

    private final ProtocolReportRepository repository;
    private final ProtocolReportMapper mapper;
    private final ConclusionService conclusionService;
    private final TableService tableService;
    private final SubsectionService subsectionService;

    @Override
    public void save(Section section, SectionTemplate sectiontemplate, SurveyJournal surveyJournal) {
        Map<Integer, ProtocolReportTemplate> templates = sectiontemplate.getProtocolReportTemplates().stream()
                                        .collect(Collectors.toMap(ProtocolReportTemplate::getSequentialNumber, t -> t));
        repository.saveAll(sectiontemplate.getProtocolReportTemplates().stream()
                .map(t -> mapper.mapToProtocolReport(t, section
                                                      , conclusionService.save(templates.get(t.getSequentialNumber())
                                                                                        .getConclusionTemplate())))
                .toList())
                .forEach(protocol -> {
                    ProtocolReportTemplate template = templates.get(protocol.getSequentialNumber());
                    if (template.getSectionTemplate() != null) {
                        subsectionService.saveForProtocolReport(protocol, template, surveyJournal);
                    }
                    if (template.getTableTemplates() != null) {
                        tableService.savaForProtocolReport(protocol, template.getTableTemplates(), surveyJournal);
                    }
                });
    }
}