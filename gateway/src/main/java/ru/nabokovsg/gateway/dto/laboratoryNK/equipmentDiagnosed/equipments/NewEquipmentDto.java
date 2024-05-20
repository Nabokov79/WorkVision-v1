package ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.equipments;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации об оборудовании")
public class NewEquipmentDto {

    @Schema(description = "Индентификатор котельной, цтп")
    @NotNull(message = "building id should not be null")
    @Positive(message = "building id can only be positive")
    private Long buildingId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Старый или новый бак-аккумулятор")
    private Boolean old;
    @Schema(description = "Модель")
    private String model;
}
