package ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения измеренного значения параметра дефекта")
public class ParameterMeasurementDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор параметра")
    private Long parameterId;
    @Schema(description = "Значение параметра")
    private Double value;
    @Schema(description = "Направление дефекта")
    private String direction;

    @Override
    public String toString() {
        return "ParameterMeasurementDto{" +
                "id=" + id +
                ", parameterId=" + parameterId +
                ", value=" + value +
                ", direction='" + direction + '\'' +
                '}';
    }
}