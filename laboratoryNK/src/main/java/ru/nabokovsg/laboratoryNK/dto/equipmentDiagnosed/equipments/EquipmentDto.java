package ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения информации об оборудовании")
public class EquipmentDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор котельной, цтп")
    private Long buildingId;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Стационарный номер")
    private Integer stationaryNumber;
    @Schema(description = "Объем")
    private Integer volume;
    @Schema(description = "Старый или новый бак-аккумулятор")
    private Boolean old;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Колличество мест проведения измерений геодезии")
    private Integer geodesyLocations;
}