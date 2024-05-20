package ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные результаты измерений твердости металла элементов оборудования")
public class ResponseHardnessMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Наименование подэлемента")
    private String partName;
    @Schema(description = "Номер измерения(по схеме)")
    private Integer measurementNumber;
    @Schema(description = "Значение твердости элемента")
    private Integer measurementValue;
    @Schema(description = "Значение допустимости твердости металла")
    private Boolean acceptableValue;
}