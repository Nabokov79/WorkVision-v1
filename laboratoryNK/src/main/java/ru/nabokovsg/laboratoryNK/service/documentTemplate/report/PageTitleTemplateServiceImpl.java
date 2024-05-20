package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.pageTitle.ResponsePageTitleTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report.PageTitleTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.report.PageTitleTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentTypeService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.DocumentHeaderTemplateService;

@Service
@RequiredArgsConstructor
public class PageTitleTemplateServiceImpl implements PageTitleTemplateService {

    private final PageTitleTemplateRepository repository;
    private final PageTitleTemplateMapper mapper;
    private final DocumentHeaderTemplateService documentHeaderService;
    private final ReportTemplateService reportService;
    private final DiagnosticDocumentTypeService documentTypeService;

    @Override
    public ResponsePageTitleTemplateDto save(PageTitleTemplateDto pageTitleDto) {
        reportService.validateByIds(pageTitleDto.getDocumentTypeId(), pageTitleDto.getEquipmentTypeId());
        PageTitleTemplate template =
                repository.save(mapper.mapToPageTitleTemplate(pageTitleDto
                        , documentTypeService.getById(pageTitleDto.getDocumentTypeId())
                        , documentHeaderService.getAllByDocumentTypeId(pageTitleDto.getDocumentTypeId())));
        reportService.save(pageTitleDto.getDocumentTypeId(), pageTitleDto.getEquipmentTypeId(), template);
        return mapper.mapToResponsePageTitleTemplateDto(template);
    }

    @Override
    public ResponsePageTitleTemplateDto update(PageTitleTemplateDto pageTitleDto) {
        if (repository.existsById(pageTitleDto.getId())) {
            return mapper.mapToResponsePageTitleTemplateDto(
                    repository.save(mapper.mapToPageTitleTemplate(pageTitleDto
                                    , documentTypeService.getById(pageTitleDto.getDocumentTypeId())
                                    , documentHeaderService.getAllByDocumentTypeId(pageTitleDto.getDocumentTypeId())))
            );
        }
        throw new NotFoundException(
                String.format("PageTitle template with id=%s not found for update", pageTitleDto.getId()));
    }

    @Override
    public ResponsePageTitleTemplateDto get(Long id) {
        return mapper.mapToResponsePageTitleTemplateDto(
                repository.findById(id).orElseThrow(
                        () -> new NotFoundException(String.format("PageTitle template with id=%s not found", id))));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("PageTitle template with id=%s not found for delete", id));
    }
}