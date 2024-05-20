package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.ResponseTableTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.table.TableTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;

import java.util.List;

public interface TableTemplateService {

    ResponseTableTemplateDto save(TableTemplateDto tableDto);

    ResponseTableTemplateDto update(TableTemplateDto tableDto);

    ResponseTableTemplateDto get(Long id);

    TableTemplate getById(Long id);

    void delete(Long id);

    void addProtocolReportTemplate(ProtocolReportTemplate template, List<Long> tableTemplatesId);

    void addProtocolTemplate(SurveyProtocolTemplate template, List<Long> tableTemplatesId);

    void addProtocolControlTemplate(ProtocolControlTemplate template, List<Long> tableTemplatesId);
}