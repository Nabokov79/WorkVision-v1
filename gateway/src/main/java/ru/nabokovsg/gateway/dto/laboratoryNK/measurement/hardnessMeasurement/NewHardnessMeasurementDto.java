package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.hardnessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления результата выполнения измерений твердости металла элементов оборудования")
public class NewHardnessMeasurementDto {

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
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Номер измерения(по схеме)")
    @NotNull(message = "measurement number should not be null")
    @Positive(message = "measurement number can only be positive")
    private Integer measurementNumber;
    @Schema(description = "Диаметр элемента(для трубопроводов)")
    private Integer diameter;
    @Schema(description = "Значение твердости металла")
    @NotNull(message = "measurement value should not be null")
    @Positive(message = "measurement value can only be positive")
    private Integer measurementValue;
}