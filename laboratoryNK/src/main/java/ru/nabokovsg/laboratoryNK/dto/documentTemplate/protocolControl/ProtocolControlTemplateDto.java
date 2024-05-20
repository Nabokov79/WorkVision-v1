package ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления/изменения протокола по результатам контроля качества")
public class ProtocolControlTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Индентификатор типа отчетного документа")
    private Long documentTypeId;
    @Schema(description = "Индентификатор подразделов протокола")
    private List<Long> subsectionTemplatesId;
    @Schema(description = "Индентификатор таблиц протокола")
    private List<Long> tableTemplatesId;
}