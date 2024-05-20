package ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Краткие данные протокола/заключения по обследованию")
public class ShortResponseSurveyProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок")
    private ResponseDocumentHeaderTemplateDto leftHeaderTemplate;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String subtitle;
}