package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения средства измерения")
public class DocumentationTemplateDto {

    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Индентификатор шаблона нормативно-технической документации")
    @NotNull(message = "template id should not be null")
    @Positive(message = "template id can only be positive")
    private Long templateId;
}