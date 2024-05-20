package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения колонки столбца")
public class UpdateColumnHeaderTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "subsection id should not be null")
    @Positive(message = "subsection id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер столбца")
    @NotNull(message = "column sequential number should not be null")
    @Positive(message = "column sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Заголовок столбца")
    @NotBlank(message = "column heading should not be blank")
    private String heading;
    @Schema(description = "Тип заголовка столбца колонки")
    @NotBlank(message = "column header type should not be blank")
    private String columnHeaderType;
}