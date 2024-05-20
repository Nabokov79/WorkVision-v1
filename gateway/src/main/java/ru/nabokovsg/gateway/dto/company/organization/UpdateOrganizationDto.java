package ru.nabokovsg.gateway.dto.company.organization;

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
@Schema(description = "Данные для измененния информации об организации")
public class UpdateOrganizationDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "id organization should not be blank")
    @Positive(message = "id organization must be positive")
    private Long id;
    @Schema(description = "Полное наименование организации")
    @NotBlank(message = "organization should not be blank")
    private String fullName;
    @Schema(description = "Краткое наименование организации")
    @NotBlank(message = "short name organization should not be blank")
    private String shortName;
    @Schema(description = "Индентификатор адреса")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id can only be positive")
    private Long addressId;
}