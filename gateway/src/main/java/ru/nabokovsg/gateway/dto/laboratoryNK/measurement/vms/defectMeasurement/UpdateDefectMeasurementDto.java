package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.defectMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.parameterMeasurement.UpdateParameterMeasurementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результаты измерения дефекта элемента, подэлемента оборудования")
public class UpdateDefectMeasurementDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id should not be null")
    @Positive(message = "id can only be positive")
    private Long id;
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
    @Schema(description = "Индентификатор дефекта")
    @NotNull(message = "defect id should not be null")
    @Positive(message = "defect id can only be positive")
    private Long defectId;
    @Schema(description = "Измеренные параметры дефекта")
    private List<@Valid UpdateParameterMeasurementDto> parameterMeasurements;
}