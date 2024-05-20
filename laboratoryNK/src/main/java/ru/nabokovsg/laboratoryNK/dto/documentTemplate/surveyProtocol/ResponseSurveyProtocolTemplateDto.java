package ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.ResponseSubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.ResponseTableTemplateDto;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные шаблона протокола/заключения по обследованию")
public class ResponseSurveyProtocolTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок")
    private Set<ResponseDocumentHeaderTemplateDto> leftHeaderTemplate;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String subtitle;
    @Schema(description = "Шаблоны подразделов")
    private List<ResponseSubsectionTemplateDto> subsectionTemplates;
    @Schema(description = "Шаблоны таблиц")
    private List<ResponseTableTemplateDto> tableTemplates;
    @Schema(description = "Заключение по результатм")
    private ResponseConclusionTemplateDto conclusionTemplate;
    @Schema(description = "Приложения")
    private List<AppendicesTemplateDto> appendices;
}