package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ShortResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report.ProtocolReportTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.report.ProtocolReportTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentTypeService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.ConclusionTemplateService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.SubsectionTemplateService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.TableTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolReportTemplateServiceImpl implements ProtocolReportTemplateService {

    private final ProtocolReportTemplateRepository repository;
    private final ProtocolReportTemplateMapper mapper;
    private final DiagnosticDocumentTypeService documentTypeService;
    private final SubsectionTemplateService subsectionTemplateService;
    private final TableTemplateService tableTemplateService;
    private final ConclusionTemplateService conclusionTemplateService;

    @Override
    public ShortResponseProtocolReportTemplateDto save(ProtocolReportTemplateDto protocolDto) {
        ProtocolReportTemplate protocol = repository.findByDocumentTypeId(protocolDto.getDocumentTypeId());
        if (protocol == null) {
            protocol = repository.save(mapper.mapToProtocolReportTemplate(protocolDto
                                                     , documentTypeService.getById(protocolDto.getDocumentTypeId())
                                 , conclusionTemplateService.getByDocumentTypeId(protocolDto.getDocumentTypeId())));
            subsectionTemplateService.addProtocolReportTemplate(protocol, protocolDto.getSubsectionTemplatesId());
            tableTemplateService.addProtocolReportTemplate(protocol, protocolDto.getTableTemplatesId());
        }
        return mapper.mapToShortProtocolReportTemplateDto(protocol);
    }

    @Override
    public ShortResponseProtocolReportTemplateDto update(ProtocolReportTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            ProtocolReportTemplate protocol = repository.save(mapper.mapToProtocolReportTemplate(protocolDto
                    , documentTypeService.getById(protocolDto.getDocumentTypeId())
                    , conclusionTemplateService.getByDocumentTypeId(protocolDto.getDocumentTypeId())));
            subsectionTemplateService.addProtocolReportTemplate(protocol, protocolDto.getSubsectionTemplatesId());
            tableTemplateService.addProtocolReportTemplate(protocol, protocolDto.getTableTemplatesId());
            return mapper.mapToShortProtocolReportTemplateDto(repository.save(protocol));
        }
        throw new NotFoundException(
                String.format("ProtocolReportTemplate with id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public ResponseProtocolReportTemplateDto get(Long id) {
        return mapper.mapToResponseProtocolReportTemplateDto(getById(id));
    }

    @Override
    public List<ShortResponseProtocolReportTemplateDto> getAll(Long id) {
        return repository.findAllBySectionId(id)
                         .stream()
                         .map(mapper::mapToShortProtocolReportTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Protocol report template with id=%s not found for delete", id));
    }

    @Override
    public void addSectionTemplate(SectionTemplate template, List<Long> protocolReportTemplatesId) {
        repository.saveAll(getAllById(protocolReportTemplatesId)
                  .stream()
                  .map(p -> mapper.mapWithSectionTemplate(p, template))
                  .toList());
    }

    private ProtocolReportTemplate getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Protocol report template with id=%s not found", id))
        );
    }

    private List<ProtocolReportTemplate> getAllById(List<Long> protocolReportTemplatesId) {
        List<ProtocolReportTemplate> templates = repository.findAllById(protocolReportTemplatesId);
        if (templates.isEmpty()) {
            throw new NotFoundException(
                    String.format("Protocol report template with ids=%s not found", protocolReportTemplatesId));
        }
        return templates;
    }
}