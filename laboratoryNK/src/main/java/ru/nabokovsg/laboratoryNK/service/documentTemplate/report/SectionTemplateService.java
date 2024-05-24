package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.SectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

import java.util.List;
import java.util.Set;

public interface SectionTemplateService {

    ResponseSectionTemplateDto save(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto update(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto get(Long id);

    List<ShortResponseSectionTemplateDto> getAll(Long id);

    Set<SectionTemplate> getAllByIds(Long documentTypeId, Long equipmentTypeId);

    Set<SectionTemplate> addReportTemplate(ReportTemplate reportTemplate, Set<SectionTemplate> templates);

    void delete(Long id);
}