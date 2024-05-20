package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.ResponsePageTitleTemplateDto;

public interface PageTitleTemplateService {

    ResponsePageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto);

    ResponsePageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto);

    ResponsePageTitleTemplateDto get(Long id);

    void delete(Long id);
}