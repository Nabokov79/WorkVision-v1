package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

public interface AppendicesTemplateService {

    ResponseAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto);

    ResponseAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto);

    void delete(Long id);

    void addReportTemplate(ReportTemplate reportTemplate);

    void addProtocolTemplate(SurveyProtocolTemplate protocolTemplate);
}