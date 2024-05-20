package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocolControl;

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
@Schema(description = "Данные для добавления протокола по результатам контроля качества")
public class NewProtocolControlTemplateDto {

    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "document type id should not be null")
    @Positive(message = "document type id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Индентификаторы подразделов")
    @NotEmpty(message = "subsection template id list should not be empty")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификаторы таблицы")
    @NotEmpty(message = "table template id list should not be empty")
    private List<Long> tableTemplatesId;
}