package ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.element;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.standardSize.NewStandardSizeDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления элемента оборудования")
public class NewElementDto {

    @Schema(description = "Индентификатор оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Наименование элемента")
    @NotBlank(message = "elementName should not be blank")
    private String elementName;
    @Schema(description = "Типоразмер")
    private NewStandardSizeDto standardSize;
}