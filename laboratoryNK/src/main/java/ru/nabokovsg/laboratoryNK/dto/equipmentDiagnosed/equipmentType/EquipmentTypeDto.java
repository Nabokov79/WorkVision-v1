package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipmentType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные типа оборудования")
public class EquipmentTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование")
    private String equipmentName;
    @Schema(description = "Модель")
    private String model;
}