package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.pageTitle;

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
@Schema(description = "Данные для измененния данных титульного листа")
public class UpdatePageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "section template id should not be null")
    @Positive(message = "section template id can only be positive")
    private Long id;
    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = " equipmentType id should not be null")
    @Positive(message = "equipmentTypee id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Строка наименования оборудования")
    @NotBlank(message = "equipment text should not be blank")
    private String equipmentText;
    @Schema(description = "Строка наименования места расположения оборудования")
    @NotBlank(message = "installation location should not be blank")
    private String installationLocation;
    @Schema(description = "Населенный пункт")
    @NotBlank(message = "city should not be blank")
    private String city;
}