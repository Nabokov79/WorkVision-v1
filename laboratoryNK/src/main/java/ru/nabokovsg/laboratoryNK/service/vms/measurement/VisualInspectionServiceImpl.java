package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.mapper.vms.measurement.VisualInspectionMapper;
import ru.nabokovsg.laboratoryNK.service.vms.EquipmentSurveyService;

@Service
@RequiredArgsConstructor
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionMapper mapper;
    private final EquipmentSurveyService vmSurveyService;

    @Override
    public ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto) {
        return mapper.mapToResponseVisualInspectionDto(vmSurveyService.saveWithVisualInspection(inspectionDto));
    }

    @Override
    public ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto) {
        return mapper.mapToResponseVisualInspectionDto(vmSurveyService.saveWithVisualInspection(inspectionDto));
    }
}