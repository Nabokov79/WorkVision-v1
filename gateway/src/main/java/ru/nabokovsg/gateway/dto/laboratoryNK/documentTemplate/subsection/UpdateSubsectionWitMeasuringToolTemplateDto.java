package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.subsection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения шаблона подраздела с данными приборов измерений и средств контроля")
public class UpdateSubsectionWitMeasuringToolTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "subsection id should not be null")
    @Positive(message = "subsection id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Данные средств контроля и измерений")
    private List<@Valid DocumentationTemplateDto> measuringTools;
}