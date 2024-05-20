package ru.nabokovsg.laboratoryNK.service.documentTemplate.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.SectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.section.ShortResponseSectionTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report.SectionTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.report.SectionTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.SubsectionTemplateService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionTemplateServiceImpl implements SectionTemplateService {

    private final SectionTemplateRepository repository;
    private final SectionTemplateMapper mapper;
    private final ProtocolReportTemplateService protocolReportTemplateService;
    private final SubsectionTemplateService subsectionTemplateService;

    @Override
    public ResponseSectionTemplateDto save(SectionTemplateDto sectionDto) {
        SectionTemplate template = repository.save(mapper.mapToSectionTemplate(sectionDto));
        protocolReportTemplateService.addSectionTemplate(template, sectionDto.getProtocolReportTemplatesId());
        subsectionTemplateService.addSectionTemplate(template, sectionDto.getSubsectionTemplatesId());
        return mapper.mapToResponseSectionTemplateDto(template);
    }

    @Override
    public ResponseSectionTemplateDto update(SectionTemplateDto sectionDto) {
        if (repository.existsById(sectionDto.getId())) {
            SectionTemplate template = repository.save(mapper.mapToSectionTemplate(sectionDto));
            protocolReportTemplateService.addSectionTemplate(template, sectionDto.getProtocolReportTemplatesId());
            subsectionTemplateService.addSectionTemplate(template, sectionDto.getSubsectionTemplatesId());
            return mapper.mapToResponseSectionTemplateDto(template);
        }
        throw new NotFoundException(
                String.format("SectionTemplate with id=%s not found for update", sectionDto.getId())
        );
    }

    @Override
    public ResponseSectionTemplateDto get(Long id) {
        return mapper.mapToResponseSectionTemplateDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(String.format("Section template with id= %s not found", id)))));
    }

    @Override
    public List<ShortResponseSectionTemplateDto> getAll(Long id) {
        return repository.findByReportTemplateId(id)
                         .stream()
                         .map(mapper::mapToShortResponseSectionTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SectionTemplate with id=%s not found for delete", id));
    }
}
