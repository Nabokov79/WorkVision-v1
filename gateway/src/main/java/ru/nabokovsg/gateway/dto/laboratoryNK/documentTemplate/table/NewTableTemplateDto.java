package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления шаблона таблицы")
public class NewTableTemplateDto {

    @Schema(description = "Тип таблицы")
    @NotBlank(message = " table type should not be blank")
    private String tableType;
    @Schema(description = "Порядковый номер таблицы")
    @NotNull(message = " table sequential number should not be null")
    @Positive(message = " table sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Текст перед таблицей")
    private String textBeforeTable;
    @Schema(description = "Текст после таблицы")
    private String textAfterTable;
    @Schema(description = "Шаблоны колонок таблицы")
    private List<@Valid NewColumnHeaderTemplateDto> columnHeaders;
}