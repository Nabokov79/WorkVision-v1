package ru.nabokovsg.gateway.dto.laboratoryNK.norms.geodesy;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Данные для изменения значений норм " +
        "допустимых отклонений значений геодезических измерений")
public class NewAcceptableDeviationsGeodesyDto {

    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipmentType id should not be null")
    @Positive(message = "equipmentType id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Полное или пустое оборудование")
    @NotNull(message = "full should not be null")
    private Boolean full;
    @Schema(description = "Старое или новое оборудование")
    @NotNull(message = "old should not be null")
    private Boolean old;
    @Schema(description = "Объем оборудования")
    @NotNull(message = "volume should not be null")
    @Positive(message = "volume can only be positive")
    private Integer volume;
    @Schema(description = "Максимальная допустимая осадка")
    @NotNull(message = "acceptablePrecipitation should not be null")
    @Positive(message = "acceptablePrecipitation can only be positive")
    private Integer acceptablePrecipitation;
    @Schema(description = "Максимальная допустимая разность для соседних точек)")
    @NotNull(message = "maxDifferenceNeighboringPoints should not be null")
    @Positive(message = "maxDifferenceNeighboringPoints can only be positive")
    private Integer maxDifferenceNeighboringPoints;
    @Schema(description = "Максимальная допустимая разность для диаметральных точек")
    @NotNull(message = "maxDifferenceDiametricPoints should not be null")
    @Positive(message = "maxDifferenceDiametricPoints can only be positive")
    private Integer maxDifferenceDiametricPoints;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Integer measurementError;
    @Schema(description = "Колличество мест проведения измерений")
    @NotNull(message = "numberLocations should not be null")
    @Positive(message = "numberLocationss can only be positive")
    private Integer numberLocations;
}