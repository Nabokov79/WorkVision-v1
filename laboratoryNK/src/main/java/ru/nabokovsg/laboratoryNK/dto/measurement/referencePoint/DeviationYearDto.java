package ru.nabokovsg.laboratoryNK.dto.measurement.referencePoint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные отклонения репера по годам")
public class DeviationYearDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Год выполненнения измерений")
    private Integer year;
    @Schema(description = "Отклонение по реперу")
    private Integer deviation;
}
