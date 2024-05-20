package ru.nabokovsg.gateway.dto.laboratoryNK.norms.measuredParameter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации о параметре измерения")
public class NewMeasuredParameterDto {

    @Schema(description = "Наименование параметра")
    @NotBlank(message = "measuredParameter should not be blank")
    private String measuredParameter;
    @Schema(description = "Единица измерения параметра")
    @NotBlank(message = "unitMeasurement should not be blank")
    private String unitMeasurement;
    @Schema(description = "Требуемые вычисления параметров дефекта")
    @NotBlank(message = "typeCalculation should not be blank")
    private String typeCalculation;
}