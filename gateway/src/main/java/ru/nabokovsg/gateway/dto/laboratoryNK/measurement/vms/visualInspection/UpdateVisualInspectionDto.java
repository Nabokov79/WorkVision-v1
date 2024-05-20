package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.visualInspection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные мзменения результатов визуального осмотра элементов оборудования")
public class UpdateVisualInspectionDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор записи в журнале обследований")
    @NotNull(message = "surveyJournal id should not be null")
    @Positive(message = "surveyJournal id can only be positive")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Результат визуального осмотра элемента")
    @NotBlank(message = "inspection should not be blank")
    private String inspection;
}