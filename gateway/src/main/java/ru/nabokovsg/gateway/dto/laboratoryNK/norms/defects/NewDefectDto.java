package ru.nabokovsg.gateway.dto.laboratoryNK.norms.defects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.measuredParameter.NewMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации о дефекте")
public class NewDefectDto {

    @Schema(description = "Наименование дефекта")
    @NotBlank(message = "defectName should not be blank")
    private String defectName;
    @Schema(description = "Указание недопустимости дефекта")
    @NotNull(message = "notMeetRequirements should not be null")
    private Boolean notMeetRequirements;
    @Schema(description = "Требуемые вычисления параметров ремонта элемента")
    @NotBlank(message = "typeCalculation should not be blank")
    private String typeCalculation;
    @Schema(description = "Использовать дефект для расчета остаточной толщины элемента")
    @NotNull(message = "useCalculateThickness should not be null")
    private Boolean useCalculateThickness;
    @Schema(description = "Измеряемые параметры ремонта элемента")
    private List<@Valid NewMeasuredParameterDto> measuredParameters;
}