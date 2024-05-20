package ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.ResponseSubsectionTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Полные данные раздела документа")
public class ResponseSectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Название")
    private String sectionName;
    @Schema(description = "Шаблоны подразделов")
    private List<ResponseSubsectionTemplateDto> subsectionTemplates;
    @Schema(description = "Шаблоны протоколов отчета")
    private List<ResponseProtocolReportTemplateDto> protocolReportTemplates;
}