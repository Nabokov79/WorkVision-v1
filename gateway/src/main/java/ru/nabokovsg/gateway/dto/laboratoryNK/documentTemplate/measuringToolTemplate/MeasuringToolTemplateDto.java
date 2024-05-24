package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.measuringToolTemplate;

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
@Schema(description = "Данные для добавления средства измерения")
public class MeasuringToolTemplateDto {

    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Индентификатор шаблона нормативно-технической документации")
    @NotNull(message = "template id should not be null")
    @Positive(message = "template id can only be positive")
    private Long templateId;
}