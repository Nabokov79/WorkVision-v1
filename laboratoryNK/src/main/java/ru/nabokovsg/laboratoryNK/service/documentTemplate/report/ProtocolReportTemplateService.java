package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ShortResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

import java.util.List;

public interface ProtocolReportTemplateService {

    ShortResponseProtocolReportTemplateDto save(ProtocolReportTemplateDto protocolDto);

    ShortResponseProtocolReportTemplateDto update(ProtocolReportTemplateDto protocolDto);

    ResponseProtocolReportTemplateDto get(Long id);

    List<ShortResponseProtocolReportTemplateDto> getAll(Long id);

    void delete(Long id);

    void addSectionTemplate(SectionTemplate template, List<Long> protocolReportTemplatesId);
}