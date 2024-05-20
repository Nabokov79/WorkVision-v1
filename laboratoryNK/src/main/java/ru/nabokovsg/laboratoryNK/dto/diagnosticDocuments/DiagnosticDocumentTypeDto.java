package ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения вида документа диагностики")
public class DiagnosticDocumentTypeDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String subtitle;
    @Schema(description = "Тип документа")
    private String typeDocument;
}