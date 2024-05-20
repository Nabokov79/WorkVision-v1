package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

@Mapper(componentModel = "spring")
public interface AppendicesTemplateMapper {

    AppendicesTemplate mapToAppendicesTemplate(AppendicesTemplateDto appendicesDto, String nameOfList);

    ResponseAppendicesTemplateDto mapToResponseAppendicesDto(AppendicesTemplate appendices);

    @Mapping(source = "reportTemplate", target = "reportTemplate")
    AppendicesTemplate mapWithReportTemplate(@MappingTarget AppendicesTemplate appendices
                                                          , ReportTemplate reportTemplate);

    @Mapping(source = "protocolTemplate", target = "surveyProtocolTemplate")
    AppendicesTemplate mapWithProtocolTemplate(@MappingTarget AppendicesTemplate appendices
                                                            , SurveyProtocolTemplate protocolTemplate);
}