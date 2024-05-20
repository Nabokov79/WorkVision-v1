package ru.nabokovsg.gateway.dto.laboratoryNK.common.laboratoryCertificate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для изменения информации об аттестации лаборатории")
public class UpdateLaboratoryCertificateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "laboratory certificate id should not be null")
    @Positive(message = "laboratory certificate id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор структурного подразделения организации")
    @NotNull(message = "division id should not be null")
    @Positive(message = "division id can only be positive")
    private Long divisionId;
    @Schema(description = "Вид выданного документа")
    @NotBlank(message = "document type should not be blank")
    private String documentType;
    @Schema(description = "Номер документа")
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата начала действия документа")
    @NotNull(message = "start date id should not be null")
    private LocalDate startDate;
    @Schema(description = "Дата окончания действия документа")
    @NotNull(message = "end date id should not be null")
    private LocalDate endDate;
    @Schema(description = "Организация, выдавшая документ")
    @NotBlank(message = "organization name should not be blank")
    private String organization;
}