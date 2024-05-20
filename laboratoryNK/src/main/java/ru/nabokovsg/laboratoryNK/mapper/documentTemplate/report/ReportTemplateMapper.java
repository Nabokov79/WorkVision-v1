package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    ReportTemplate mapToReportTemplate(Long documentTypeId, Long equipmentTypeId, PageTitleTemplate template);

    ResponseReportTemplateDto mapToResponseReportTemplateDto(ReportTemplate reportTemplate);

    ShortResponseReportTemplateDto mapToShortResponseReportTemplateDto(PageTitleTemplate pageTitleTemplate);
}