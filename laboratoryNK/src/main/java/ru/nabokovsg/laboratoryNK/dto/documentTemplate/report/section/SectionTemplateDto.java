package ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные для добавления/изменения заголовка раздела документа")
public class SectionTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор шаблона отчета")
    private Long reportTemplateId;
    @Schema(description = "Порядковый номер")
    private Integer sequentialNumber;
    @Schema(description = "Название")
    private String sectionName;
    @Schema(description = "Указать в разделе данные паспорта оборудования")
    private Boolean specifyEquipmentPassport;
    @Schema(description = "Индентификаторы подразделов")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификаторы протоколов отчета")
    private List<Long> protocolReportTemplatesId;
}