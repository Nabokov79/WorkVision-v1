package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentationTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения шаблона нормативно-технической документации")
public class UpdateDocumentationTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequentialNumber should not be null")
    @Positive(message = "sequentialNumber can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Индентификатор нормативно-технической документации")
    @NotNull(message = "documentationId id should not be null")
    @Positive(message = "documentationId id can only be positive")
    private Long documentationId;
}