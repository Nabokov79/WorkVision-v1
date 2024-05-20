package ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.diagnosticDocumentsType;

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
@Schema(description = "Данные для изменения вида документа диагностики")
public class UpdateDiagnosticDocumentTypeDto {

    @Schema(description = "Индентификатор")
    @NotNull(message = "document header id should not be null")
    @Positive(message = "document header id can only be positive")
    private Long id;
    @Schema(description = "Название документа")
    @NotBlank(message = "title should not be blank")
    private String title;
    @Schema(description = "Заголовок документа")
    @NotBlank(message = "subtitle should not be blank")
    private String subtitle;
    @Schema(description = "Тип документа")
    @NotBlank(message = "type document should not be blank")
    private String typeDocument;
}