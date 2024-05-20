package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.columnHeader.ColumnHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

import java.util.List;

public interface ColumnHeaderTemplateService {

    List<ColumnHeaderTemplate> save(TableTemplate tableTemplate, List<ColumnHeaderTemplateDto> columnHeaderTemplatesDto);

    List<ColumnHeaderTemplate> update(TableTemplate tableTemplate, List<ColumnHeaderTemplateDto> columnHeaderTemplatesDto);
}