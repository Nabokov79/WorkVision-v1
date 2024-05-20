package ru.nabokovsg.laboratoryNK.dto.documentTemplate.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон отчета")
public class ResponseReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Титульный лист")
    private ResponsePageTitleTemplateDto pageTitleTemplate;
    @Schema(description = "Подразделы")
    private Set<ResponseSectionTemplateDto> sectionsTemplate;
    @Schema(description = "Приложения")
    private Set<AppendicesTemplateDto> appendices;
}