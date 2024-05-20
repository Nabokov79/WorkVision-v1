package ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.equipmentType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные типа оборудования")
public class NewEquipmentTypeDto {

    @Schema(description = "Наименование")
    @NotBlank(message = "equipmentName should not be blank")
    private String equipmentName;
    @Schema(description = "Модель")
    private String model;
}