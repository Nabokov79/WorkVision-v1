package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ReportTemplateMapper {

    @Mapping(source = "documentTypeId", target = "documentTypeId")
    @Mapping(source = "equipmentTypeId", target = "equipmentTypeId")
    @Mapping(source = "pageTitleTemplate", target = "pageTitleTemplate")
    @Mapping(source = "appendices", target = "appendices")
    @Mapping(target = "id", ignore = true)
    ReportTemplate mapToReportTemplate(Long documentTypeId
                                     , Long equipmentTypeId
                                     , PageTitleTemplate pageTitleTemplate
                                     , Set<AppendicesTemplate> appendices);

    ResponseReportTemplateDto mapToResponseReportTemplateDto(ReportTemplate reportTemplate);

    ShortResponseReportTemplateDto mapToShortResponseReportTemplateDto(PageTitleTemplate pageTitleTemplate);
}