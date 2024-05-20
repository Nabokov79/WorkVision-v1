package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report.SectionMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.report.SectionRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.SubsectionService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.DocumentEquipmentPassportService;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository repository;
    private final SectionMapper mapper;
    private final ProtocolReportService protocolReportService;
    private final SubsectionService subsectionService;
    private final DocumentEquipmentPassportService passportService;

    @Override
    public void save(Report report, ReportTemplate template, SurveyJournal surveyJournal) {
        Map<Integer, SectionTemplate> sectionsTemplates = template.getSectionsTemplate().stream()
                .collect(Collectors.toMap(SectionTemplate::getSequentialNumber, s -> s));
        repository.saveAll(template.getSectionsTemplate().stream()
                                                  .map(s -> mapper.mapToSection(s, report))
                                                  .toList()).forEach(s -> {
            SectionTemplate sectiontemplate = sectionsTemplates.get(s.getSequentialNumber());
            if (sectiontemplate.getSpecifyEquipmentPassport()) {
                passportService.saveForSection(s,surveyJournal);
            }
            if (!sectiontemplate.getSubsectionTemplates().isEmpty()) {
                subsectionService.saveForSection(s, sectiontemplate, surveyJournal);
            }
            if (!sectiontemplate.getProtocolReportTemplates().isEmpty()) {
                protocolReportService.save(s, sectiontemplate, surveyJournal);
            }
        });
    }
}
