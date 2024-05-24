package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.ShortResponseReportTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report.ReportTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.*;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.report.ReportTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.AppendicesTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportTemplateServiceImpl implements ReportTemplateService {

    private final ReportTemplateRepository repository;
    private final ReportTemplateMapper mapper;
    private final PageTitleTemplateService pageTitleTemplateService;
    private final SectionTemplateService sectionTemplateService;
    private final AppendicesTemplateService appendicesTemplateService;

    @Override
    public ResponseReportTemplateDto create(Long documentTypeId, Long equipmentTypeId) {
        if (repository.existsByDocumentTypeIdAndEquipmentTypeId(documentTypeId, equipmentTypeId)) {
            throw new BadRequestException(
                   String.format("ReportTemplate by documentTypeId=%s and equipmentTypeId=%s is create", documentTypeId
                                                                                                    , equipmentTypeId));
        }
        PageTitleTemplate pageTitleTemplate = pageTitleTemplateService.getByIds(documentTypeId, equipmentTypeId);
        ReportTemplate template = repository.save(mapper.mapToReportTemplate(documentTypeId
                                                                           , equipmentTypeId
                                                                           , pageTitleTemplate));
        template.setSectionsTemplate(sectionTemplateService.addReportTemplate(template
                                    , sectionTemplateService.getAllByIds(documentTypeId
                                                                       , equipmentTypeId)));
        template.setAppendices(appendicesTemplateService.addReportTemplate(template
                                                 , appendicesTemplateService.getAllByEquipmentTypeId(equipmentTypeId)));
        return mapper.mapToResponseReportTemplateDto(template);
    }

    @Override
    public ResponseReportTemplateDto get(Long id) {
        return mapper.mapToResponseReportTemplateDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("ReportTemplate with id=%s not found", id))));
    }

    @Override
    public List<ShortResponseReportTemplateDto> getAll() {
        return repository.findAllPageTitleTemplate().stream().map(mapper::mapToShortResponseReportTemplateDto).toList();
    }

    @Override
    public ReportTemplate getByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId) {
        return repository.findByDocumentTypeIdAndEquipmentTypeId(documentTypeId, equipmentTypeId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("ReportTemplate by documentTypeId=%s and equipmentTypeId=%s not found"
                                                                                           , documentTypeId
                                                                                           , equipmentTypeId)));
    }

    @Override
    public ReportTemplate getByDocumentTypeId(Long documentTypeId) {
        return repository.findByDocumentTypeId(documentTypeId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("ReportTemplate by documentTypeId=%s  not found", documentTypeId)));
    }
}