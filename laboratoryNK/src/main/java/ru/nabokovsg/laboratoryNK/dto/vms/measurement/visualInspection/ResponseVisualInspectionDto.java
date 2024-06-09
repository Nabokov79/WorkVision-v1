package ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные визуального осмотра элементов оборудования")
public class ResponseVisualInspectionDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Результат визуального осмотра элемента")
    private String inspection;
}