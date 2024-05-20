package ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон заключений документа")
public class ResponseConclusionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Основной текст положительного заключения")
    private String positiveText;
    @Schema(description = "Основной текст отрицательного заключения")
    private String negativeText;
}