package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данныедля добавления/изменения информации паспорта оборудования")
public class EquipmentPassportDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    private String header;
    @Schema(description = "Значение")
    private String meaning;
    @Schema(description = "Указать в протоколе")
    private Boolean useToProtocol;
}