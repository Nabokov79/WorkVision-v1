package ru.nabokovsg.laboratoryNK.dto.norms.defects;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.norms.measuredParameter.ResponseMeasuredParameterDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные дефекта")
public class ResponseDefectDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Наименование дефекта")
    private String defectName;
    @Schema(description = "Измеряемые параметры дефекта")
    private List<ResponseMeasuredParameterDto> measuredParameters;
}