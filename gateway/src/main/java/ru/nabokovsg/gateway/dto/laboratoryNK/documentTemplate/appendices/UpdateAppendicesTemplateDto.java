package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.appendices;

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
@Schema(description = "Данные для изменения информации о приложении к документу")
public class UpdateAppendicesTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "appendices id should not be null")
    @Positive(message = "appendices id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number id can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    @NotBlank(message = "appendices name should not be blank")
    private String appendicesName;
}