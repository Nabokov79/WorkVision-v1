package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.SectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SectionTemplateMapper {

    @Mapping(source = "sectionDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "sectionDto.sectionName", target = "sectionName")
    @Mapping(source = "sectionDto.specifyEquipmentPassport", target = "specifyEquipmentPassport")
    @Mapping(source = "section.subsectionTemplates", target = "subsectionTemplates")
    @Mapping(source = "section.protocolReportTemplates", target = "protocolReportTemplates")
    @Mapping(source = "section.reportTemplate", target = "reportTemplate")
    @Mapping(source = "section.id", target = "id")
    SectionTemplate mapToSectionTemplate(SectionTemplateDto sectionDto, SectionTemplate section);

    @Mapping(source = "sectionDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "sectionDto.sectionName", target = "sectionName")
    @Mapping(source = "sectionDto.specifyEquipmentPassport", target = "specifyEquipmentPassport")
    @Mapping(source = "reportTemplate", target = "reportTemplate")
    @Mapping(target = "id", ignore = true)
    SectionTemplate mapToNewSectionTemplate(SectionTemplateDto sectionDto, ReportTemplate reportTemplate);

    ResponseSectionTemplateDto mapToResponseSectionTemplateDto(SectionTemplate section);

    ShortResponseSectionTemplateDto mapToShortResponseSectionTemplateDto(SectionTemplate section);
}