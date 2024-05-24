package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    @Mapping(source = "documentTypeId", target = "documentTypeId")
    @Mapping(source = "equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "pageTitleTemplate", target = "pageTitleTemplate")
    @Mapping(target = "id", ignore = true)
    ReportTemplate mapToReportTemplate(Long documentTypeId
                                     , Long equipmentTypeId
                                     , PageTitleTemplate pageTitleTemplate);

    ResponseReportTemplateDto mapToResponseReportTemplateDto(ReportTemplate reportTemplate);

    ShortResponseReportTemplateDto mapToShortResponseReportTemplateDto(PageTitleTemplate pageTitleTemplate);
}