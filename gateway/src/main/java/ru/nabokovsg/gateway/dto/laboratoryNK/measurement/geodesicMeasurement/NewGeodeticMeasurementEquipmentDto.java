package ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Результат измерений в одном месте проведения геодезии(нивелирования)")
public class NewGeodeticMeasurementEquipmentDto {

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
    @Schema(description = "Результаты выполнения геодезии(нивелировании)")
    private List<@Valid NewGeodesyMeasurementDto> measurements;
}