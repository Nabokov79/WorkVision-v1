package ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableThickness;

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
@Schema(description = "Данные для добавления допустимых толщин элементов оборудования")
public class NewAcceptableThicknessDto {

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
    @Schema(description = "Диаметр элемента(для трубопроводов)")
    private Integer diameter;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    @NotNull(message = "minThickness should not be null")
    @Positive(message = "minThickness can only be positive")
    private Double minThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    private Integer acceptablePercent;
    @Schema(description = "Допустимая погрешность измерения")
    @NotNull(message = "measurementError should not be null")
    @Positive(message = "measurementError can only be positive")
    private Float measurementError;
}