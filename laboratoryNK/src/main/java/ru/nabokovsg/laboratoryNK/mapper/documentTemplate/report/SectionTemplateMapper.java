package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.SectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    SectionTemplate mapToSectionTemplate(SectionTemplateDto sectionDto);

    ResponseSectionTemplateDto mapToResponseSectionTemplateDto(SectionTemplate section);

    ShortResponseSectionTemplateDto mapToShortResponseSectionTemplateDto(SectionTemplate section);
}