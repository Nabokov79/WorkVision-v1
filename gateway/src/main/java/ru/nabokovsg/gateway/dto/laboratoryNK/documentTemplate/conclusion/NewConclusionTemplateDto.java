package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.conclusion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Данные для добавления шаблона заключения документа")
public class NewConclusionTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "document type id should not be blank")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Основной текст положительного заключения")
    @NotBlank(message = "positive text should not be blank")
    private String positiveText;
    @Schema(description = "Основной текст отрицательного заключения")
    @NotBlank(message = "negative text should not be blank")
    private String negativeText;
}