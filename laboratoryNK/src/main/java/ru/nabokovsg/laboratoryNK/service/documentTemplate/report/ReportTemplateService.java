package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;

import java.util.List;

public interface ReportTemplateService {

    ResponseReportTemplateDto get(Long id);

    List<ShortResponseReportTemplateDto> getAll();

    void save(Long documentTypeId, Long equipmentTypeId, PageTitleTemplate template);

    void validateByIds(Long documentTypeId, Long equipmentTypeId);

    ReportTemplate getByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId);
}