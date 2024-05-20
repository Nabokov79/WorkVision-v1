package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.subsection;

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
@Schema(description = "Данные для добавления/изменения данных структурного подразделения в подразделе документа")
public class DivisionDataDto {

    @Schema(description = "Тип структурного подразделения")
    @NotBlank(message = "division type should not be blank")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = "division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Пользовательское название структурного подразделения организации")
    private String userDivisionName;
    @Schema(description = "Указать адресс структурного подразделения")
    @NotNull(message = "address should not be null")
    private Boolean specifyAddress;
    @Schema(description = "Указать лицензию/аттестацию структурного подразделения")
    @NotNull(message = "certificate should not be null")
    private Boolean specifyCertificate;
}