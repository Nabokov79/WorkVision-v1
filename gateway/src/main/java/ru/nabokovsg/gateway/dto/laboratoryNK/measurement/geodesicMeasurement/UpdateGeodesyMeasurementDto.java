package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения результатов выполнения геодезии(нивелировании)")
public class UpdateGeodesyMeasurementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер измерения")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Номер места проведения измерения")
    @NotNull(message = "numberMeasurementLocation should not be null")
    @Positive(message = "numberMeasurementLocation can only be positive")
    private Integer numberMeasurementLocation;
    @Schema(description = "Измеренное значение высоты по реперу")
    private Integer referencePointValue;
    @Schema(description = "Измеренное значение высоты по контрольной точке")
    @NotNull(message = "controlPointValue should not be null")
    @Positive(message = "controlPointValue can only be positive")
    private Integer controlPointValue;
    @Schema(description = "Измеренное значение при смене положения прибора(переход)")
    private Integer transitionValue;
}