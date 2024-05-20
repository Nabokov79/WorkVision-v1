package ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные обследования оборудования")
public class ResponseEquipmentRepairDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата выполнения ремонта")
    private String date;
    @Schema(description = "Описание выполненного ремонта")
    private String description;
    @Schema(description = "Организация, выполнившая ремонта")
    private String organization;
}