package ru.nabokovsg.laboratoryNK.service.documentTemplate.protocol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ShortResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.protocol.ProtocolControlTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.protocol.ProtocolControlTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentTypeService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.DocumentHeaderTemplateService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.SubsectionTemplateService;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.TableTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocolControlTemplateServiceImpl implements ProtocolControlTemplateService {

    private final ProtocolControlTemplateRepository repository;
    private final ProtocolControlTemplateMapper mapper;
    private final DocumentHeaderTemplateService documentHeaderService;
    private final DiagnosticDocumentTypeService documentTypeService;
    private final SubsectionTemplateService subsectionService;
    private final TableTemplateService tableService;

    @Override
    public ShortResponseProtocolControlTemplateDto save(ProtocolControlTemplateDto protocolDto) {
        if (repository.existsByDocumentTypeId(protocolDto.getDocumentTypeId())) {
            throw new BadRequestException(
                    String.format("ReportControlTemplate with documentTypeId=%s is found"
                                                                                    , protocolDto.getDocumentTypeId()));
        }
        ProtocolControlTemplate template = repository.save(mapper.mapToProtocolTemplate(protocolDto
                , documentTypeService.getById(protocolDto.getDocumentTypeId())
                , documentHeaderService.getAllByDocumentTypeId(protocolDto.getDocumentTypeId())));
        addProtocolControlTemplate(template, protocolDto);
        return mapper.mapToShortResponseProtocolTemplateDto(template);
    }

    @Override
    public ShortResponseProtocolControlTemplateDto update(ProtocolControlTemplateDto protocolDto) {
        if (repository.existsById(protocolDto.getId())) {
            ProtocolControlTemplate template = repository.save(mapper.mapToProtocolTemplate(protocolDto
                    , documentTypeService.getById(protocolDto.getDocumentTypeId())
                    , documentHeaderService.getAllByDocumentTypeId(protocolDto.getDocumentTypeId())));
            addProtocolControlTemplate(template, protocolDto);
            return mapper.mapToShortResponseProtocolTemplateDto(template);
        }
        throw new NotFoundException(
                String.format("Protocol template by id=%s not found for update", protocolDto.getId())
        );
    }

    @Override
    public ResponseProtocolControlTemplateDto get(Long id) {
        return mapper.mapToResponseProtocolTemplateDto(repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Protocol template by id=%s not found", id))));
    }

    @Override
    public List<ShortResponseProtocolControlTemplateDto> getAll() {
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

    private void addProtocolControlTemplate(ProtocolControlTemplate template, ProtocolControlTemplateDto protocolDto) {
        subsectionService.addProtocolControlTemplate(template, protocolDto.getSubsectionTemplatesId());
        tableService.addProtocolControlTemplate(template, protocolDto.getTableTemplatesId());
    }
}