package ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения шаблона протокола отчета")
public class ProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    private Long documentTypeId;
    @Schema(description = "Порядковый номер протокола")
    private Integer sequentialNumber;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterSubtitle;
    @Schema(description = "Индентификаторы подразделов")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификаторы таблицы")
    private List<Long> tableTemplatesId;
}