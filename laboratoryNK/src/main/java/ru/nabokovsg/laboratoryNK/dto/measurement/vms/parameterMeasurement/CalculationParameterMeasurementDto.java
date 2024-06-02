package ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Измеряемый параметр")
public class CalculationParameterMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование параметра")
    private String parameterName;
    @Schema(description = "Рассчитанное минимальное значение параметра")
    private Double firstValue;
    @Schema(description = "Рассчитанное максимальное значение параметра")
    private Double secondValue;
    @Schema(description = "Единица измерения параметра")
    private String unitMeasurement;
    @Schema(description = "Рассчитанное значение параметра")
    private Double parameterValue;
}