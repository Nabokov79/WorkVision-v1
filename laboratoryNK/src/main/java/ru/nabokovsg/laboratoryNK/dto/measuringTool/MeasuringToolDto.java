package ru.nabokovsg.laboratoryNK.dto.measuringTool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения информации о измерительном инструменте(приборе)")
public class MeasuringToolDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название")
    private String toll;
    @Schema(description = "Модель")
    private String model;
    @Schema(description = "Заводской номер")
    private String workNumber;
    @Schema(description = "Назначение")
    private String purpose;
    @Schema(description = "Дата изготовления")
    private LocalDate manufacturing;
    @Schema(description = "Дата начала эксплуатации")
    private LocalDate exploitation;
    @Schema(description = "Организация-изготовитель")
    private String manufacturer;
    @Schema(description = "Диапазон измерений")
    private String  measuringRange;
    @Schema(description = "Характеристики")
    private String characteristics;
    @Schema(description = "Владелец средства измерения(прибора)")
    private String owner;
    @Schema(description = "Дата поверки")
    private LocalDate verificationDate;
    @Schema(description = "Дата следующей поверки")
    private LocalDate nextVerificationDate;
    @Schema(description = "Mетрологическая организация")
    private String organization;
    @Schema(description = "Номер свидетельства о поверке")
    private String certificateNumber;
    @Schema(description = "Номер госреестра")
    private String registry;
    @Schema(description = "Примечание")
    private String note;
    @Schema(description = "Вид контроля")
    private String controlType;
    @Schema(description = "Индентификатор сотрудника")
    private Long employeeId;
}