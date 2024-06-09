package ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement.ParameterMeasurementDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результаты измерения дефекта элемента, подэлемента оборудования")
public class DefectMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор записи в журнале обследований")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    private Long equipmentId;
    @Schema(description = "Индентификатор элемента")
    private Long elementId;
    @Schema(description = "Индентификатор подэлемента")
    private Long partElementId;
    @Schema(description = "Индентификатор дефекта")
    private Long defectId;
    @Schema(description = "Измеренные параметры дефекта")
    private List<ParameterMeasurementDto> parameterMeasurements;
}