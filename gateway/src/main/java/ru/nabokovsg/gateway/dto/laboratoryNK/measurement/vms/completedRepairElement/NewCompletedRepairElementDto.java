package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.completedRepairElement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.parameterMeasurement.NewParameterMeasurementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Рассчитанные результаты измерения ремонта элемента, подэлемента оборудования")
public class NewCompletedRepairElementDto {

    @Schema(description = "Индентификатор записи в журнале обследований")
    @NotNull(message = "surveyJournal id should not be null")
    @Positive(message = "surveyJournal id can only be positive")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    @NotNull(message = "equipment id should not be null")
    @Positive(message = "equipment id can only be positive")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    @NotNull(message = "element id should not be null")
    @Positive(message = "element id can only be positive")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Индентификатор ремонта")
    @NotNull(message = "repair id should not be null")
    @Positive(message = "repair id can only be positive")
    private Long repairId;
    @Schema(description = "Измеренные параметры выполненного ремонта элемента")
    private List<@Valid NewParameterMeasurementDto> parameterMeasurements;
}