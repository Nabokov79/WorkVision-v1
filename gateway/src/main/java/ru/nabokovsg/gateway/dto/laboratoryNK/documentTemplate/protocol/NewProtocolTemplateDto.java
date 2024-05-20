package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления шаблона протокола по обследованию")
public class NewProtocolTemplateDto {

    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = "equipment type id should not be null")
    @Positive(message = "equipment type id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Индентификаторы подразделов")
    @NotEmpty(message = "subsection template id list should not be empty")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификаторы таблицы")
    @NotEmpty(message = "table template id list should not be empty")
    private List<Long> tableTemplatesId;
}