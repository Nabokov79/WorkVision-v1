package ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.section;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Данные для добавления раздела с протоколами")
public class NewSectionWithProtocolTemplateDto {

    @Schema(description = "Индентификатор типа документа")
    @NotNull(message = "documentType id should not be null")
    @Positive(message = "documentType id can only be positive")
    private Long documentTypeId;
    @Schema(description = "Индентификатор типа оборудования")
    @NotNull(message = " equipmentType id should not be null")
    @Positive(message = "equipmentType id can only be positive")
    private Long equipmentTypeId;
    @Schema(description = "Порядковый номер")
    @NotNull(message = "sequential number should not be null")
    @Positive(message = "sequential number can only be positive")
    private Integer sequentialNumber;
    @Schema(description = "Наименование раздела")
    @NotBlank(message = "section name should not be blank")
    private String sectionName;
    @Schema(description = "Индентификаторы протоколов отчета")
    @NotEmpty(message = "protocol id list should not be empty")
    private List<Long> protocolReportTemplatesId;
}
