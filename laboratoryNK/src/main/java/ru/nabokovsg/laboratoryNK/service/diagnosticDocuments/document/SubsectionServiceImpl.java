package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.SubsectionMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.SubsectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubsectionServiceImpl implements SubsectionService {

    private final SubsectionRepository repository;
    private final SubsectionMapper mapper;
    private final RegulatoryDocumentationService documentationService;
    private final DocumentMeasuringToolService measuringToolService;
    private final TableService tableService;

    @Override
    public void saveForSection(Section section, SectionTemplate sectiontemplate, SurveyJournal surveyJournal) {
        List<Subsection> subsections = new ArrayList<>();
        sectiontemplate.getSubsectionTemplates().forEach(s -> {
                    if (s.getTableTemplate() != null) {
                        subsections.add(mapper.mapWithTable(s, section,
                                tableService.savaForSubsection(s.getTableTemplate(), surveyJournal)));
                    } else {
                         subsections.add(mapper.mapToSubsection(s, section));
                    }
                });
        common(repository.saveAll(subsections), sectiontemplate.getSubsectionTemplates(), surveyJournal);
    }

    @Override
    public void saveForProtocolReport(ProtocolReport protocol, ProtocolReportTemplate template, SurveyJournal surveyJournal) {
        common(repository.saveAll(template.getSubsectionTemplates().stream()
                                                     .map( s -> mapper.mapWithProtocolReport(s, protocol))
                                                     .toList())
                , template.getSubsectionTemplates()
                , surveyJournal);
    }

    private void common(List<Subsection> subsections, Set<SubsectionTemplate> subsectionTemplates, SurveyJournal surveyJournal) {
        Map<Integer, SubsectionTemplate> templates = subsectionTemplates.stream()
                .collect(Collectors.toMap(SubsectionTemplate::getSequentialNumber, s -> s));
        subsections.forEach(subsection -> {
            SubsectionTemplate template = templates.get(subsection.getSequentialNumber());
            if (!template.getDocumentationTemplate().isEmpty()) {
                documentationService.save(subsection, template.getDocumentationTemplate());
            }
            if (!template.getMeasuringToolsTemplates().isEmpty()) {
                measuringToolService.save(subsection, surveyJournal.getEmployees(), template.getMeasuringToolsTemplates());
            }
        });
    }
}