package ru.nabokovsg.laboratoryNK.dto.measurement.utm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результаты ультразвукового измерения толщины элемента, подэлемента оборудования")
public class ResponseUltrasonicThicknessMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Диаметр элемента(для трубопровода)")
    private Integer diameter;
    @Schema(description = "Номер измерения(по схеме)")
    private Integer measurementNumber;
    @Schema(description = "Минимальное измереноое значение")
    private Double minMeasurementValue;
    @Schema(description = "Максимальное измеренное значение ")
    private Double maxMeasurementValue;
}