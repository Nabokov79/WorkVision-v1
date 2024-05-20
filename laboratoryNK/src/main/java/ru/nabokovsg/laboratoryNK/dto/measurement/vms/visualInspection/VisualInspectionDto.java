package ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные добавления/изменения результатов визуального осмотра элементов оборудования")
public class VisualInspectionDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи в журнале обследований")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Результат визуального осмотра элемента")
    private String inspection;
}