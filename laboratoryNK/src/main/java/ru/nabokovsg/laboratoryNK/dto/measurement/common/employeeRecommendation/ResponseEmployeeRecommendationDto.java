package ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные рекомендации")
public class ResponseEmployeeRecommendationDto {

    @Schema(description = "Индентификатор")
    Long id;
    @Schema(description = "Текст рекомендации")
    String recommendationText;
}