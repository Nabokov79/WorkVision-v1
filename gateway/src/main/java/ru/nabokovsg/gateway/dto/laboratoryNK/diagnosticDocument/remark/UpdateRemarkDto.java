package ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.remark;

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
@Schema(description = "Данные для изменения замечания к документу или чертежу")
public class UpdateRemarkDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "remark id should not be null")
    @Positive(message = "remark id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор документа")
    @NotNull(message = "document id should not be null")
    @Positive(message = "document id can only be positive")
    private Long documentId;
    @Schema(description = "Замечание к документу или чертежу")
    @NotBlank(message = "remark should not be blank")
    private String remark;
    @Schema(description = "Индентификатор сотрудника")
    @NotNull(message = "employee id should not be null")
    @Positive(message = "employee id can only be positive")
    private Long employeeId;
    @Schema(description = "Отметка об исправлении замечания")
    private Boolean documentCorrected;
}