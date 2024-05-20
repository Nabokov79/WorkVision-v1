package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType.ResponseEquipmentTypeDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные оборудования")
public class ResponseShortEquipmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Тип оборудования")
    private ResponseEquipmentTypeDto equipmentType;
    @Schema(description = "Полное наименование")
    private String fullName;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Модель")
    private String model;
}