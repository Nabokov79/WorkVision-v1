package ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения результата выполнения" +
                      " измерений твердости металла элементов оборудования")
public class HardnessMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи журнала задач")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор типа оборудования")
    private Long equipmentTypeId;
    @Schema(description = "Индентификатор оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Номер измерения(по схеме)")
    private Integer measurementNumber;
    @Schema(description = "Диаметр элемента(для трубопроводов)")
    private Integer diameter;
    @Schema(description = "Значение твердости металла")
    private Integer measurementValue;
}