package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения информации о подэлементе")
public class PartElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Наименование подэлемента")
    private String partName;
    @Schema(description = "Типоразмер")
    private StandardSizeDto standardSize;
}