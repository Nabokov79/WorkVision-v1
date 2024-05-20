package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные паспорта оборудования")
public class ResponseEquipmentPassportDto {

    @Schema(description = "Индентификатор")
    private long id;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Наименование")
    private String header;
    @Schema(description = "Значение")
    private String meaning;
}