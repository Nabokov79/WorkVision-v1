package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.parameterMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Изменить измеренное значение параметра дефекта")
public class UpdateParameterMeasurementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор параметра")
    @NotNull(message = "parameter id should not be null")
    @Positive(message = "parameter id can only be positive")
    private Long parameterId;
    @Schema(description = "Значение параметра")
    @NotNull(message = "value should not be null")
    @Positive(message = "value can only be positive")
    private Double value;
}