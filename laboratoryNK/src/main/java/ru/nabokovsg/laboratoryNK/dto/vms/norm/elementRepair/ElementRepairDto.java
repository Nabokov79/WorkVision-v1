package ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.measuredParameter.MeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные нового способа ремонта")
public class ElementRepairDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование типа ремонта")
    private String repairName;
    @Schema(description = "Требуемые вычисления параметров ремонта элемента")
    private String typeCalculation;
    @Schema(description = "Измеряемые параметры ремонта элемента")
    private List<MeasuredParameterDto> measuredParameters;
}