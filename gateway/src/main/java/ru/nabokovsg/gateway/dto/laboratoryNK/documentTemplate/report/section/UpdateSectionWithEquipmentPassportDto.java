package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения раздела раздела паспортных данных оборудования")
public class UpdateSectionWithEquipmentPassportDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "section template id should not be null")
    @Positive(message = "section template id can only be positive")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование раздела")
    @NotBlank(message = "section name should not be blank")
    private String sectionName;
    @Schema(description = "Указать в разделе данные паспорта оборудования")
    @NotNull(message = "specifyEquipmentPassport should not be null")
    private Boolean specifyEquipmentPassport;
}