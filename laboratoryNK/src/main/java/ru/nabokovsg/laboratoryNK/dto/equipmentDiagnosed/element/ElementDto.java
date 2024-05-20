package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения элемента оборудования")
public class ElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Наименование элемента")
    private String elementName;
    @Schema(description = "Типоразмер")
    private StandardSizeDto standardSize;
}