package ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения рекомендации")
public class EmployeeRecommendationDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор дианостируемого оборудования")
    private Long equipmentId;
    @Schema(description = "Текст рекомендации")
    private String recommendationText;
}