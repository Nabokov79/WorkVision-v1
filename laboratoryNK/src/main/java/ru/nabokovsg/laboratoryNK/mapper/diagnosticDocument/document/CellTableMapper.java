package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.CellTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;

@Mapper(componentModel = "spring")
public interface CellTableMapper {

    @Mapping(source = "columnSequentialNumber", target = "columnSequentialNumber")
    @Mapping(source = "stringSequentialNumber", target = "stringSequentialNumber")
    @Mapping(source = "cellValue", target = "cellValue")
    @Mapping(source = "table", target = "table")
    @Mapping(target = "id", ignore = true)
    CellTable mapToCellTable(Integer columnSequentialNumber
                           , Integer stringSequentialNumber
                           , String cellValue
                           , DocumentTable table);

    @Mapping(source = "mergeLines", target = "mergeLines")
    @Mapping(source = "columnSequentialNumber", target = "columnSequentialNumber")
    @Mapping(source = "stringSequentialNumber", target = "stringSequentialNumber")
    @Mapping(source = "cellValue", target = "cellValue")
    @Mapping(source = "table", target = "table")
    @Mapping(target = "id", ignore = true)
    CellTable mapToElementCell(
              Integer mergeLines
            , Integer columnSequentialNumber
            , Integer stringSequentialNumber
            , String cellValue
            , DocumentTable table);
}