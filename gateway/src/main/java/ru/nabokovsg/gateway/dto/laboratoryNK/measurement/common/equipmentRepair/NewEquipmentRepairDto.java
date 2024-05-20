package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.equipmentRepair;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные обследования оборудования")
public class NewEquipmentRepairDto {

    @Schema(description = "Дата проведения обследования")
    @NotBlank(message = "date should not be blank")
    private String date;
    @Schema(description = "Описание выполненного ремонта")
    @NotBlank(message = "description should not be blank")
    private String description;
    @Schema(description = "Организация, выполнившая обследование")
    @NotBlank(message = " organization should not be blank")
    private String organization;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    @NotNull(message = "equipmentDiagnosed id should not be null")
    @Positive(message = "equipmentDiagnosed id can only be positive")
    private Long equipmentDiagnosedId;
}