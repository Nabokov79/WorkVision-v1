package ru.nabokovsg.gateway.dto.laboratoryNK.norms.elementRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.measuredParameter.NewMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные способа ремонта")
public class NewElementRepairDto {

    @Schema(description = "Наименование типа ремонта")
    @NotBlank(message = "repairName should not be blank")
    private String repairName;
    @Schema(description = "Требуемые вычисления параметров ремонта элемента")
    @NotBlank(message = "typeCalculation should not be blank")
    private String typeCalculation;
    @Schema(description = "Измеряемые параметры ремонта элемента")
    private List<@Valid NewMeasuredParameterDto> measuredParameters;
}