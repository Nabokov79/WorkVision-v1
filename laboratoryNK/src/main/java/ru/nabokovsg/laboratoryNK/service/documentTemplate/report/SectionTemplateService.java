package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.SectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ShortResponseSectionTemplateDto;

import java.util.List;

public interface SectionTemplateService {

    ResponseSectionTemplateDto save(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto update(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto get(Long id);

    List<ShortResponseSectionTemplateDto> getAll(Long id);

    void delete(Long id);
}