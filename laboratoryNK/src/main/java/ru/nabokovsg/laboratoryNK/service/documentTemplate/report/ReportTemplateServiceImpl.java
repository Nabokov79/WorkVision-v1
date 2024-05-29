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
    private final AppendicesTemplateService appendicesTemplateService;

    @Override
    public void create(PageTitleTemplate pageTitleTemplate) {
        if (repository.existsByDocumentTypeIdAndEquipmentTypeId(pageTitleTemplate.getDocumentTypeId()
                                                              , pageTitleTemplate.getEquipmentTypeId())) {
            throw new BadRequestException(
                   String.format("ReportTemplate by documentTypeId=%s and equipmentTypeId=%s is create"
                                                           , pageTitleTemplate.getDocumentTypeId()
                                                           , pageTitleTemplate.getEquipmentTypeId()));
        }
        repository.save(mapper.mapToReportTemplate(pageTitleTemplate.getDocumentTypeId()
                                                 , pageTitleTemplate.getEquipmentTypeId()
                                                 , pageTitleTemplate
                                                 , appendicesTemplateService.getAllByEquipmentTypeId(
                                                                             pageTitleTemplate.getEquipmentTypeId())));

    }

    @Override
    public ResponseReportTemplateDto get(Long id) {
        return mapper.mapToResponseReportTemplateDto(getById(id));
    }

    @Override
    public List<ShortResponseReportTemplateDto> getAll() {
        return repository.findAllPageTitleTemplate().stream().map(mapper::mapToShortResponseReportTemplateDto).toList();
    }

    @Override
    public ReportTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("ReportTemplate with id=%s not found", id)));
    }

    @Override
    public ReportTemplate getByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId) {
        return repository.findByDocumentTypeIdAndEquipmentTypeId(documentTypeId, equipmentTypeId)
                .orElseThrow(() -> new NotFoundException(
                String.format("ReportTemplate with documentTypeId=%s and equipmentTypeId=%s not found"
                                                                 , documentTypeId, equipmentTypeId)));
    }
}