package ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результаты измерения ремонта элемента, подэлемента оборудования")
public class CompletedRepairElementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи в журнале обследований")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагносттируемого оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Индентификатор ремонта")
    private Long repairId;
    @Schema(description = "Измеренные параметры выполненного ремонта элемента")
    private List<ParameterMeasurementDto> parameterMeasurements;
}