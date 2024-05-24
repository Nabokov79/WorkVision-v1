package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

import java.util.List;

public interface ReportTemplateService {

    ResponseReportTemplateDto create(Long documentTypeId, Long equipmentTypeId);

    ResponseReportTemplateDto get(Long id);

    List<ShortResponseReportTemplateDto> getAll();

    ReportTemplate getByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId);

    ReportTemplate getByDocumentTypeId(Long documentTypeId);
}