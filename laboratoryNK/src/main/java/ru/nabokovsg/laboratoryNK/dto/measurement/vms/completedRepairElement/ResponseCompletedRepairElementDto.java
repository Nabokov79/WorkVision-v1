package ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.CalculationParameterMeasurementDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные результаты измерения ремонта элемента, подэлемента оборудования")
public class ResponseCompletedRepairElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Наименование подэлемента")
    private String partName;
    @Schema(description = "Наименование ремонта")
    private String repairName;
    @Schema(description = "Рассчитанные параметры выполненного ремонта элемента")
    private Set<CalculationParameterMeasurementDto> parameterMeasurements;
}