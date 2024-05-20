package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.utm;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Добавление результата ультразвукового измерения толщины стенки элемента" +
        ", подэлемента оборудования")
public class NewUltrasonicThicknessMeasurementDto {

    @Schema(description = "Индентификатор записи в журнале обследований")
    @NotNull(message = "surveyJournal id should not be null")
    @Positive(message = "surveyJournal id can only be positive")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Диаметр элемента(для трубопровода)")
    private Integer diameter;
    @Schema(description = "Номер измерения по схеме")
    @NotNull(message = "measurement number should not be null")
    @Positive(message = "measurement number can only be positive")
    private Integer measurementNumber;
    @Schema(description = "Минимальное измереноое значение")
    @NotNull(message = "min measurement value should not be null")
    @Positive(message = "min measurement value can only be positive")
    private Double minMeasurementValue;
    @Schema(description = "Максимальное измеренное значение ")
    @NotNull(message = "max measurement value should not be null")
    @Positive(message = "max measurement value can only be positive")
    private Double maxMeasurementValue;
}