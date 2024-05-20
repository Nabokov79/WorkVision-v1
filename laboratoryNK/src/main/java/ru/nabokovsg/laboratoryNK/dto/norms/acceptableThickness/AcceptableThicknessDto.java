package ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения допустимых толщин элементов оборудования")
public class AcceptableThicknessDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор элемента оборудования")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента оборудования")
    private Long partElementId;
    @Schema(description = "Диаметр элемента(для трубопроводов)")
    private Integer diameter;
    @Schema(description = "Минимальная допустимая толщина стенки элемента")
    private Double minThickness;
    @Schema(description = "Минимальная допустимая толщина стенки элемента в процентах")
    private Integer acceptablePercent;
    @Schema(description = "Допустимая погрешность измерения")
    private Float measurementError;
}
