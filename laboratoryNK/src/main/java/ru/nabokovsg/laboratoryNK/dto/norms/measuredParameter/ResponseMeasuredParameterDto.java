package ru.nabokovsg.laboratoryNK.dto.norms.measuredParameter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Измеряемый параметр")
public class ResponseMeasuredParameterDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование параметра")
    private String parameterName;
    @Schema(description = "Единица измерения параметра")
    private String unitMeasurement;
    @Schema(description = "Требуемые вычисления параметров дефекта")
    private String typeCalculation;
}