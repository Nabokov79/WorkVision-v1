package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.MeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.ResponseMeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.MeasuringToolTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.MeasuringToolTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasuringToolTemplateServiceImpl implements MeasuringToolTemplateService {

    private final MeasuringToolTemplateRepository repository;
    private final MeasuringToolTemplateMapper mapper;
    private final StringBuilderService stringBuilderService;

    @Override
    public void save(MeasuringTool measuringTool) {
        if (!repository.existsByTollAndModel(measuringTool.getToll(), measuringTool.getModel())) {
            repository.save(
                    mapper.mapToMeasuringToolTemplate(measuringTool
                            , stringBuilderService.buildMeasuringToolTemplate(measuringTool))
            );
        }
    }

    @Override
    public void update(MeasuringTool measuringTool) {
        MeasuringToolTemplate template = repository.findByMeasuringToolId(measuringTool.getId());
        if (template != null) {
            repository.save(
                    mapper.mapToUpdateMeasuringToolTemplate(template
                                                      , measuringTool
                                                      , stringBuilderService.buildMeasuringToolTemplate(measuringTool))
            );
        }
    }

    @Override
    public void saveWithSubsectionTemplate(SubsectionTemplate template, List<MeasuringToolTemplateDto> measuringTools) {
        Map<Long, MeasuringToolTemplate> templates = repository.findAllById(
                                                         measuringTools.stream()
                                                                       .map(MeasuringToolTemplateDto::getTemplateId)
                                                                       .toList())
                                                         .stream()
                                                         .collect(Collectors.toMap(MeasuringToolTemplate::getId, m -> m)
        );
        if (!measuringTools.isEmpty()) {
            repository.saveAll(measuringTools.stream()
                                             .map(m -> mapper.mapWithSubsectionTemplate(templates.get(m.getTemplateId())
                                                     , template
                                                     , m.getSequentialNumber()))
                                             .toList());
        }
    }


    @Override
    public List<ResponseMeasuringToolTemplateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseMeasuringToolTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("MeasuringTool template with id=%s not found for delete", id));
    }
}