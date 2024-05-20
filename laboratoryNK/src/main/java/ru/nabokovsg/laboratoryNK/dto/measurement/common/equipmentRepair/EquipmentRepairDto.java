package ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения информации об ремонте оборудования")
public class EquipmentRepairDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Дата выполнения ремонта")
    private String date;
    @Schema(description = "Описание выполненного ремонта")
    private String description;
    @Schema(description = "Организация, выполнившая ремонта")
    private String organization;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    private Long equipmentDiagnosedId;
}