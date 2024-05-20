package ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.partElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.standardSize.UpdateStandardSizeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения информации о подэлементе")
public class UpdatePartElementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Наименование подэлемента")
    @NotBlank(message = "partName should not be blank")
    private String partName;
    @Schema(description = "Типоразмер")
    private UpdateStandardSizeDto standardSize;
}