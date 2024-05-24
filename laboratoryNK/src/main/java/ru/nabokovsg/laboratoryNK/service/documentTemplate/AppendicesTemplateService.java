package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

import java.util.Set;

public interface AppendicesTemplateService {

    ResponseAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto);

    ResponseAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto);

    void delete(Long id);

    Set<AppendicesTemplate> getAllByEquipmentTypeId(Long equipmentTypeId);

    Set<AppendicesTemplate> addReportTemplate(ReportTemplate reportTemplate, Set<AppendicesTemplate> templates);

    void addProtocolTemplate(SurveyProtocolTemplate protocolTemplate);
}