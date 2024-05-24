package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;

public interface PageTitleTemplateService {

    ResponsePageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto);

    ResponsePageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto);

    ResponsePageTitleTemplateDto get(Long id);

    PageTitleTemplate getByIds(Long documentTypeId, Long equipmentTypeId);

    void delete(Long id);
}