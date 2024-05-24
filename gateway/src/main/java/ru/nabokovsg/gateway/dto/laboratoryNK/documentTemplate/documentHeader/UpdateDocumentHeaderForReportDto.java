package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader;

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
@Schema(description = "Данные для изменения заголовка отчета")
public class UpdateDocumentHeaderForReportDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "document header id should not be null")
    @Positive(message = "document header id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа документа диагностики")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Тип структурного подразделения организации")
    @NotBlank(message = "division type should not be blank")
    private String divisionType;
    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = "division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Указать лицензию/аттестацию")
    @NotNull(message = "specifyLicense should not be null")
    private Boolean specifyLicense;
    @Schema(description = "Указать в документе полное наименование")
    @NotNull(message = "specifyFullName should not be null")
    private Boolean specifyFullName;
    @Schema(description = "Указать адрес")
    @NotNull(message = "specifyAddress should not be null")
    private Boolean specifyAddress;
    @Schema(description = "Указать контактные данные организации")
    @NotNull(message = "specifyContacts should not be null")
    private Boolean specifyContacts;
}