package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentationTemplate;

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
@Schema(description = "Данные для добавления шаблона нормативно-технической документации")
public class NewDocumentationTemplateDto {

    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequentialNumber should not be null")
    @Positive(message = "sequentialNumber can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Индентификатор нормативно-технической документации")
    @NotNull(message = "documentationId id should not be null")
    @Positive(message = "documentationId id can only be positive")
    private Long documentationId;
}