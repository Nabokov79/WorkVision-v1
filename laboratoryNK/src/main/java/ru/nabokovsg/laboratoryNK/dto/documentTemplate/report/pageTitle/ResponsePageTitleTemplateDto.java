package ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Полные данные титульного листа")
public class ResponsePageTitleTemplateDto {

    @Schema(description = "Индентификатор")
    private Long id;
    @Schema(description = "Заголовок документа")
    private Set<ResponseDocumentHeaderTemplateDto> documentHeaders;
    @Schema(description = "Название документа")
    private String title;
    @Schema(description = "Заголовок документа")
    private String subtitle;
    @Schema(description = "Строка наименования объекта")
    private String equipmentText;
    @Schema(description = "Строка местоположения")
    private String installationLocation;
    @Schema(description = "Строка адреса")
    private String address;
    @Schema(description = "Строка подписи")
    private String signature;
    @Schema(description = "Населенный пункт")
    private String city;
}