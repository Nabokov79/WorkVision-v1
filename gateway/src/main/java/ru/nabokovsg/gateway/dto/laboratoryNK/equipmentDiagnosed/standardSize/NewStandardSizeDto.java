package ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.standardSize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления информации о типоразмере элемента или подэлемента")
public class NewStandardSizeDto {

    @Schema(description = "Проектная толщина элемента(подэлемента)")
    @NotNull(message = "designThickness should not be null")
    @Positive(message = "designThickness can only be positive")
    private Double designThickness;
    @Schema(description = "Наружный диаметр(для элемента трубопровода)")
    private Integer outerDiameter;
    @Schema(description = "Минимальный диаметр(для тройника, перехода)")
    private Integer minDiameter;
    @Schema(description = "Максимальный диаметр(для тройника, перехода)")
    private Integer maxDiameter;
}