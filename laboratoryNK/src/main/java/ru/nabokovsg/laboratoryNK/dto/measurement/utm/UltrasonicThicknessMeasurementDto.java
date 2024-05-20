package ru.nabokovsg.laboratoryNK.dto.measurement.utm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дабавлени/изменение результата ультразвукового измерения толщины стенки элемента" +
        ", подэлемента оборудования")
public class UltrasonicThicknessMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи журнала обследований")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Диаметр элемента(для трубопровода)")
    private Integer diameter;
    @Schema(description = "Номер измерения по схеме")
    private Integer measurementNumber;
    @Schema(description = "Минимальное измереноое значение")
    private Double minMeasurementValue;
    @Schema(description = "Максимальное измеренное значение ")
    private Double maxMeasurementValue;
}