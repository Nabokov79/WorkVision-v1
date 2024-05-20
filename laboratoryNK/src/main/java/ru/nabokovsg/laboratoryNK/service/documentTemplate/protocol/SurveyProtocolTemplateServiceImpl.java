package ru.nabokovsg.laboratoryNK.service.documentTemplate.protocol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ShortResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.SurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.protocol.SurveyProtocolTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.protocol.SurveyProtocolTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentTypeService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyProtocolTemplateServiceImpl implements SurveyProtocolTemplateService {

    private final SurveyProtocolTemplateRepository repository;
    private final SurveyProtocolTemplateMapper mapper;
    private final DocumentHeaderTemplateService documentHeaderService;
    private final DiagnosticDocumentTypeService documentTypeService;
    private final SubsectionTemplateService subsectionService;
    private final TableTemplateService tableService;
    private final ConclusionTemplateService conclusionService;
    private final AppendicesTemplateService appendicesTemplateService;

    @Override
    public ShortResponseSurveyProtocolTemplateDto save(SurveyProtocolTemplateDto protocolDto) {
        if (repository.existsByDocumentTypeIdAndEquipmentTypeId(protocolDto.getDocumentTypeId()
                                                              , protocolDto.getEquipmentTypeId())) {
            throw new BadRequestException(
                    String.format("ReportTemplate with documentTypeId=%s equipmentTypeId=%s is found"
                                                                                   , protocolDto.getDocumentTypeId()
                                                                                   , protocolDto.getEquipmentTypeId()));
        }
        SurveyProtocolTemplate template = repository.save(mapper.mapToProtocolTemplate(protocolDto
                                        , documentTypeService.getById(protocolDto.getDocumentTypeId())
                                        , documentHeaderService.getAllByDocumentTypeId(protocolDto.getDocumentTypeId())
                                        , conclusionService.getByDocumentTypeId(protocolDto.getDocumentTypeId())));
       addProtocolTemplate(template, protocolDto);
       return mapper.mapToShortResponseProtocolTemplateDto(template);
    }

    @Override
    public ShortResponseSurveyProtocolTemplateDto update(SurveyProtocolTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            SurveyProtocolTemplate template = repository.save(mapper.mapToProtocolTemplate(protocolDto
                    , documentTypeService.getById(protocolDto.getDocumentTypeId())
                    , documentHeaderService.getAllByDocumentTypeId(protocolDto.getDocumentTypeId())
                    , conclusionService.getByDocumentTypeId(protocolDto.getDocumentTypeId())));
            addProtocolTemplate(template, protocolDto);
            return mapper.mapToShortResponseProtocolTemplateDto(template);
        }
        throw new NotFoundException(
                String.format("Protocol template by id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public ResponseSurveyProtocolTemplateDto get(Long id) {
        return mapper.mapToResponseProtocolTemplateDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Protocol template by id=%s not found", id))));
    }

    @Override
    public List<ShortResponseSurveyProtocolTemplateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToShortResponseProtocolTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Protocol template with id=%s not found for delete", id));
    }

    private void addProtocolTemplate(SurveyProtocolTemplate template, SurveyProtocolTemplateDto protocolDto) {
        subsectionService.addProtocolTemplate(template, protocolDto.getSubsectionTemplatesId());
        tableService.addProtocolTemplate(template, protocolDto.getTableTemplatesId());
        appendicesTemplateService.addProtocolTemplate(template);
    }
}