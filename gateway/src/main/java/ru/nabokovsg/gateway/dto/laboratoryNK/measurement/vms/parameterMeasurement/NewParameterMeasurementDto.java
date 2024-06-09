package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.parameterMeasurement;

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
@Schema(description = "Добавить измеренное значение параметра дефекта")
public class NewParameterMeasurementDto {

    @Schema(description = "Индентификатор параметра")
    @NotNull(message = "parameter id should not be null")
    @Positive(message = "parameter id can only be positive")
    private Long parameterId;
    @Schema(description = "Значение параметра")
    @NotNull(message = "value should not be null")
    @Positive(message = "value can only be positive")
    private Double value;
    @Schema(description = "Направление дефекта")
    @NotBlank(message = "direction should not be blank")
    private String direction;
}