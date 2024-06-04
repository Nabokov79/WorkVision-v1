package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.VisualInspectionMapper;

@Service
@RequiredArgsConstructor
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionMapper mapper;
    private final VMSurveyService vmSurveyService;

    @Override
    public ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto) {
        return mapper.mapToResponseVisualInspectionDto(vmSurveyService.saveWithVisualInspection(inspectionDto));
    }

    @Override
    public ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto) {
        return mapper.mapToResponseVisualInspectionDto(vmSurveyService.saveWithVisualInspection(inspectionDto));
    }
}