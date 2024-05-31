package ru.nabokovsg.laboratoryNK.dto.norms.defects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.norms.measuredParameter.MeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения информации о дефекте")
public class DefectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование дефекта")
    private String defectName;
    @Schema(description = "Указание недопустимости дефекта")
    private Boolean notMeetRequirements;
    @Schema(description = "Требуемые вычисления параметров дефекта")
    private String typeCalculation;
    @Schema(description = "Использовать дефект для расчета остаточной толщины элемента")
    private Boolean useCalculateThickness;
    @Schema(description = "Измеряемые параметры дефекта")
    private List<MeasuredParameterDto> measuredParameters;
}