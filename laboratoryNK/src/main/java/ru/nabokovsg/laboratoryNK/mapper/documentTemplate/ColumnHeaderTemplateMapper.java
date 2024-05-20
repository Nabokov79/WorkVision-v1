package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.columnHeader.ColumnHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ColumnHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

@Mapper(componentModel = "spring")
public interface ColumnHeaderTemplateMapper {

    @Mapping(source = "templateDto.id", target = "id")
    @Mapping(source = "templateDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "heading", target = "heading")
    @Mapping(source = "columnHeaderType", target = "columnHeaderType")
    @Mapping(source = "tableTemplate", target = "tableTemplate")
    ColumnHeaderTemplate mapToColumnHeaderTemplates(TableTemplate tableTemplate
                                                  , ColumnHeaderTemplateDto templateDto
                                                  , String heading
                                                  , String columnHeaderType);
}