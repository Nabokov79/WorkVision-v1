package ru.nabokovsg.laboratoryNK.dto.measurement.geodesicMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результаты выполнения геодезии(нивелировании)")
public class GeodeticMeasurementEquipmentDto {

    @Schema(description = "Индентификатор записи в журнале обследований")
    private Long surveyJournalId;
    @Schema(description = "Индентификатор диагносттируемого оборудования")
    private Long equipmentId;
    @Schema(description = "Результаты выполнения геодезии(нивелировании)")
    private List<GeodesicMeasurementDto> measurements;
}