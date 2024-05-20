package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.client.LaboratoryClient;
import ru.nabokovsg.laboratoryNK.dto.client.DivisionDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.MeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.DivisionDataDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.ResponseSubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.SubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.SubsectionTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DivisionType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.SubsectionTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.common.LaboratoryCertificateService;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubsectionTemplateServiceImpl implements SubsectionTemplateService {

    private final SubsectionTemplateRepository repository;
    private final SubsectionTemplateMapper mapper;
    private final TableTemplateService tableTemplateService;
    private final RegulatoryDocumentationTemplateService documentationTemplateService;
    private final MeasuringToolTemplateService measuringToolTemplateService;
    private final LaboratoryCertificateService laboratoryCertificateService;
    private final LaboratoryClient client;
    private final StringBuilderService stringBuilderService;

    @Override
    public ResponseSubsectionTemplateDto save(SubsectionTemplateDto subsectionDto) {
        SubsectionTemplate template = mapper.mapToSubsectionTemplate(subsectionDto);
        if (subsectionDto.getDivisionParam() != null) {
            template = setDivision(template, subsectionDto.getDivisionParam());
        }
        if (subsectionDto.getTableId() != null) {
            template = setTable(template, subsectionDto.getTableId());
        }
        template = repository.save(template);
        if (subsectionDto.getDocumentations() != null && !subsectionDto.getDocumentations().isEmpty()) {
            setDocumentations(template, subsectionDto.getDocumentations());
        }
        if (subsectionDto.getMeasuringTools() != null && !subsectionDto.getMeasuringTools().isEmpty()) {
            setMeasuringTools(template, subsectionDto.getMeasuringTools());
        }
        return mapper.mapToResponseSubsectionTemplateDto(template);
    }

    @Override
    public ResponseSubsectionTemplateDto update(SubsectionTemplateDto subsectionDto) {
        if (repository.existsById(subsectionDto.getId())) {
            SubsectionTemplate template = mapper.mapToSubsectionTemplate(subsectionDto);
            if (subsectionDto.getDivisionParam() != null) {
                template = setDivision(template, subsectionDto.getDivisionParam());
            }
            if (subsectionDto.getTableId() != null) {
                template = setTable(template, subsectionDto.getTableId());
            }
            template = repository.save(template);
            if (subsectionDto.getDocumentations() != null && !subsectionDto.getDocumentations().isEmpty()) {
                setDocumentations(template, subsectionDto.getDocumentations());
            }
            if (subsectionDto.getMeasuringTools() != null && !subsectionDto.getMeasuringTools().isEmpty()) {
                setMeasuringTools(template, subsectionDto.getMeasuringTools());
            }
            return mapper.mapToResponseSubsectionTemplateDto(template);
        }
        throw new NotFoundException(
                String.format("Subsection template with id=%s not found for delete", subsectionDto.getId())
        );
    }

    @Override
    public ResponseSubsectionTemplateDto get(Long id) {
        return mapper.mapToResponseSubsectionTemplateDto(
                repository.findById(id)
                          .orElseThrow(() -> new NotFoundException(
                                  String.format("Subsection template with id=%s not found", id)))
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Subsection template with id=%s not found for delete", id));
    }


    @Override
    public void addProtocolReportTemplate(ProtocolReportTemplate template, List<Long> subsectionTemplatesId) {
        repository.saveAll(getAllByIds(subsectionTemplatesId)
                  .stream()
                  .map(s -> mapper.mapWithProtocolReportTemplate(s, template))
                  .toList());
    }

    @Override
    public void addSectionTemplate(SectionTemplate template, List<Long> subsectionTemplatesId) {
        repository.saveAll(getAllByIds(subsectionTemplatesId)
                .stream()
                .map(s -> mapper.mapWithSectionTemplate(s, template))
                .toList());
    }

    @Override
    public void addProtocolTemplate(SurveyProtocolTemplate template, List<Long> subsectionTemplatesId) {
        repository.saveAll(getAllByIds(subsectionTemplatesId)
                .stream()
                .map(s -> mapper.mapWithProtocolTemplate(s, template))
                .toList());
    }

    @Override
    public void addProtocolControlTemplate(ProtocolControlTemplate template, List<Long> subsectionTemplatesId) {
        repository.saveAll(getAllByIds(subsectionTemplatesId)
                .stream()
                .map(s -> mapper.mapWithProtocolControlTemplate(s, template))
                .toList());
    }

    private Set<SubsectionTemplate> getAllByIds(List<Long> ids) {
        Set<SubsectionTemplate> templates = new HashSet<>(repository.findAllById(ids));
        if (templates.isEmpty()) {
            throw new NotFoundException(String.format("Subsection template by ids=%s not found", ids));
        }
        return templates;
    }

    private SubsectionTemplate setDivision(SubsectionTemplate template, DivisionDataDto param) {
        DivisionDto division;
        switch (convertToDivisionType(param.getDivisionType())) {
            case BRANCH -> division = mapper.mapFromBranch(client.getBranch(param.getDivisionId()));

            case DEPARTMENT -> division = mapper.mapFromDepartment(client.getDepartment(param.getDivisionId()));
            default -> throw new BadRequestException(
                    String.format(String.format("Unknown divisionType=%s", param.getDivisionType()))
            );
        }
        return mapper.mapWithDivisionContact(template
                , stringBuilderService.buildDivision(param, division, laboratoryCertificateService.getAll()));
    }

    private SubsectionTemplate setTable(SubsectionTemplate template, Long tableId) {
        return mapper.mapWithTableTemplate(template, tableTemplateService.getById(tableId));
    }

    private void setDocumentations(SubsectionTemplate template, List<DocumentationTemplateDto> documentations) {
        documentationTemplateService.saveWithSubsectionTemplate(template, documentations);
    }

    private void setMeasuringTools(SubsectionTemplate template, List<MeasuringToolTemplateDto> measuringTools) {
        measuringToolTemplateService.saveWithSubsectionTemplate(template, measuringTools);
    }

    protected DivisionType convertToDivisionType(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown data format divisionType=%s", divisionType))
                );
    }
}