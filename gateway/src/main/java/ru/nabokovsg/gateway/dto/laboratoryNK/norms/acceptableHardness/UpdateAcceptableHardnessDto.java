package ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableHardness;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения допустимых толщин элементов оборудования")
public class UpdateAcceptableHardnessDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipmentType id should not be null")
    @Positive(message = "equipmentType id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор элемента оборудования")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента оборудования")
    private Long partElementId;
    @Schema(description = "Минимальная допустимая твердость металла элемента")
    @NotNull(message = "minHardness should not be null")
    @Positive(message = "minHardness can only be positive")
    private Integer minHardness;
    @Schema(description = "Максимальная допустимая твердость металла элемента")
    @NotNull(message = "maxHardness should not be null")
    @Positive(message = "maxHardness can only be positive")
    private Integer maxHardness;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}
