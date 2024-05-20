package ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона средства измерения")
public class ResponseMeasuringToolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название")
    private String toll;
    @Schema(description = "Модель")
    private String model;
}