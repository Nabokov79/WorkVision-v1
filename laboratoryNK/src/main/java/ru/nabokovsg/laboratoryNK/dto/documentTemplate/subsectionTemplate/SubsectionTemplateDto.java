package ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.MeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения шаблона подразделя")
public class SubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private Integer sequentialNumber;
    @Schema(description = "Наименование подраздела")
    private String subsectionName;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Данных структурного подразделения")
    private DivisionDataDto divisionParam;
    @Schema(description = "Индентификатор таблицы")
    private Long tableId;
    @Schema(description = "Индентификатор типа документа")
    private Long documentTypeId;
    @Schema(description = "Добавить сводную таблицу результатов измерений")
    private boolean summaryResults;
    @Schema(description = "Данные нормативно-технической документации")
    private List<DocumentationTemplateDto> documentations;
    @Schema(description = "Данные средств контроля и измерений")
    private List<MeasuringToolTemplateDto> measuringTools;
}