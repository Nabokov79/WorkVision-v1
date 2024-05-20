package ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.ResponseMeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.ResponseTableTemplateDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Шаблон подраздела")
public class ResponseSubsectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Порядковый номер подраздела")
    private Integer sequentialNumber;
    @Schema(description = "Наименование подраздела")
    private String subsectionName;
    @Schema(description = "Текст пользователя")
    private String userText;
    @Schema(description = "Информация о структурном подразделении")
    private String divisionContact;
    @Schema(description = "Шаблон таблицы подраздела")
    private ResponseTableTemplateDto tableTemplate;
    @Schema(description = "Нормативно-техническая документация")
    private List<ResponseDocumentationTemplateDto> documentationTemplate;
    @Schema(description = "Шаблон средств и приборов измерений")
    private List<ResponseMeasuringToolTemplateDto> measuringToolsTemplates;
}
