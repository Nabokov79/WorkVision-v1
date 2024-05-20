package ru.nabokovsg.laboratoryNK.dto.documentTemplate.table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.columnHeader.ColumnHeaderTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения шаблона таблицы")
public class TableTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип таблицы")
    private String tableType;
    @Schema(description = "Порядковый номер таблицы")
    private Integer sequentialNumber;
    @Schema(description = "Название таблицы")
    private String tableName;
    @Schema(description = "Текст перед таблицей")
    private String textBeforeTable;
    @Schema(description = "Текст после таблицы")
    private String textAfterTable;
    @Schema(description = "Шаблоны колонок таблицы")
    private List<ColumnHeaderTemplateDto> columnHeaders;
}