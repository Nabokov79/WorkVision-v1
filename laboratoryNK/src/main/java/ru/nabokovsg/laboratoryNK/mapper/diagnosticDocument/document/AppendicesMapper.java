package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Appendices;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.SurveyProtocol;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;

@Mapper(componentModel = "spring")
public interface AppendicesMapper {

    @Mapping(target = "id", ignore = true)
    Appendices mapWithReport(AppendicesTemplate template, Report report);

    @Mapping(target = "id", ignore = true)
    Appendices mapWithSurveyProtocol(AppendicesTemplate template, SurveyProtocol protocol);
}