package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления шаблона раздела паспортных данных диагностируемого оборудования")
public class NewSectionWithEquipmentPassportDto {

    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование подраздела")
    @NotBlank(message = "subsection name should not be blank")
    private String subsectionName;
    @Schema(description = "Указать в разделе данные паспорта оборудования")
    @NotNull(message = "specifyEquipmentPassport should not be null")
    private Boolean specifyEquipmentPassport;
}
