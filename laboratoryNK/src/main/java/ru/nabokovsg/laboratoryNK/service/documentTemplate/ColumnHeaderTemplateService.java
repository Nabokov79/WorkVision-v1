package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.columnHeader.ColumnHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

import java.util.List;
import java.util.Set;

public interface ColumnHeaderTemplateService {

    Set<ColumnHeaderTemplate> save(TableTemplate tableTemplate, List<ColumnHeaderTemplateDto> columnHeaderTemplatesDto);

    Set<ColumnHeaderTemplate> update(TableTemplate tableTemplate, List<ColumnHeaderTemplateDto> columnHeaderTemplatesDto);
}