package ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения информации о приложении к документу")
public class AppendicesTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    private String appendicesName;
}
