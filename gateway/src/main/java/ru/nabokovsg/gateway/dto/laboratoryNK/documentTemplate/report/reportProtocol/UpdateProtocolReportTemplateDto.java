package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.reportProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения шаблона протокола отчета")
public class UpdateProtocolReportTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "protocol report template id should not be null")
    @Positive(message = "protocol report template id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Текст пользователя после заголовка")
    private String userTextAfterSubtitle;
    @Schema(description = "Индентификаторы подразделов")
    @NotEmpty(message = "subsection template id list should not be empty")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификаторы таблицы")
    @NotEmpty(message = "table template id list should not be empty")
    private List<Long> tableTemplatesId;
}