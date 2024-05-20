package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.employeeRecommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления рекомендации")
public class NewEmployeeRecommendationDto {

    @Schema(description = "Индентификатор дианостируемого оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Текст рекомендации")
    @NotBlank(message = "recommendation text should not be blank")
    private String recommendationText;
}