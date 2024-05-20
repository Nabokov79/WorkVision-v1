package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.ResponseTableTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.TableTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;

@Mapper(componentModel = "spring")
public interface TableTemplateMapper {

    @Mapping(source = "tableDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "tableType", target = "tableType")
    @Mapping(source = "tableDto.tableName", target = "tableName")
    @Mapping(source = "tableDto.textBeforeTable", target = "textBeforeTable")
    @Mapping(source = "tableDto.textAfterTable", target = "textAfterTable")
    @Mapping(source = "tableDto.id", target = "id")
    TableTemplate mapToTableTemplate(TableTemplateDto tableDto, String tableType);

    ResponseTableTemplateDto mapToResponseTableTemplateDto(TableTemplate table);

    @Mapping(source = "protocolReportTemplate", target = "protocolReportTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    TableTemplate mapWithProtocolReportTemplate(@MappingTarget TableTemplate tableTemplate
                                                             , ProtocolReportTemplate protocolReportTemplate);

    @Mapping(source = "protocolTemplate", target = "surveyProtocolTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    TableTemplate mapWithProtocolTemplate(@MappingTarget TableTemplate tableTemplate
                                                       , SurveyProtocolTemplate protocolTemplate);

    @Mapping(source = "protocolControlTemplate", target = "protocolControlTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    TableTemplate mapWithProtocolTemplate(@MappingTarget TableTemplate tableTemplate
                                                       , ProtocolControlTemplate protocolControlTemplate);
}