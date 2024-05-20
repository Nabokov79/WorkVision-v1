package ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результат измерений в одном месте проведения геодезии(нивелирования)")
public class GeodesicMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер измерения")
    private Integer sequentialNumber;
    @Schema(description = "Номер места проведения измерения")
    private Integer numberMeasurementLocation;
    @Schema(description = "Измеренное значение высоты по реперу")
    private Integer referencePointValue;
    @Schema(description = "Измеренное значение высоты по контрольной точке")
    private Integer controlPointValue;
    @Schema(description = "Измеренное значение при смене положения прибора(переход)")
    private Integer transitionValue;
}