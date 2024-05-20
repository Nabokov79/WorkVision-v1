package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.MeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.measuringToolTemplate.ResponseMeasuringToolTemplateDto;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;

import java.util.List;

public interface MeasuringToolTemplateService {

    void save(MeasuringTool measuringTool);

    void update(MeasuringTool measuringTool);

    void saveWithSubsectionTemplate(SubsectionTemplate template, List<MeasuringToolTemplateDto> measuringTools);

    List<ResponseMeasuringToolTemplateDto> getAll();

    void delete(Long id);
}