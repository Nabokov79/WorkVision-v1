package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные элемента оборудования")
public class ResponseElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String elementName;
    @Schema(description = "Данные подэлементов")
    private Set<ResponsePartElementDto> partsElement;
    @Schema(description = "Типоразмер")
    private StandardSizeDto standardSize;
}