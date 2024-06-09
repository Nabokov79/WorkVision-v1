package ru.nabokovsg.laboratoryNK.service.vms.norm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.measuredParameter.MeasuredParameterDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.vms.norm.MeasuredParameterMapper;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.repository.vms.norm.MeasuredParameterRepository;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstParameterMeasurementService;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstUnitMeasurementService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MeasuredParameterServiceImpl implements MeasuredParameterService {

    private final MeasuredParameterRepository repository;
    private final MeasuredParameterMapper mapper;
    private final ConstParameterMeasurementService parameterMeasurementService;
    private final ConstUnitMeasurementService unitMeasurementService;

    @Override
    public Set<MeasuredParameter> saveForDefect(Defect defect, List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(parametersDto.stream()
                .map(p -> mapper.mapForDefect(
                          parameterMeasurementService.get(p.getMeasuredParameter())
                        , unitMeasurementService.get(p.getUnitMeasurement())
                        , defect
                        , getCalculationType(p.getTypeCalculation())))
                .toList()));
    }

    @Override
    public Set<MeasuredParameter> saveForElementRepair(ElementRepair repair, List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(
                parametersDto.stream()
                        .map(p -> mapper.mapForElementRepair(
                                  parameterMeasurementService.get(p.getMeasuredParameter())
                                , unitMeasurementService.get(p.getUnitMeasurement())
                                , repair
                                , getCalculationType(p.getTypeCalculation())))
                        .toList())
        );
    }

    @Override
    public Set<MeasuredParameter> update(List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(
                parametersDto.stream()
                        .map(p -> mapper. mapToUpdateMeasuredParameter(p.getId()
                                , parameterMeasurementService.get(p.getMeasuredParameter())
                                , unitMeasurementService.get(p.getUnitMeasurement())))
                        .toList())
        );
    }

    @Override
    public Set<MeasuredParameter> getAllByDefectId(Long defectId) {
        Set<MeasuredParameter> measuredParameters = repository.findAllByDefectId(defectId);
        if (measuredParameters.isEmpty()) {
            throw new NotFoundException(String.format("MeasuredParameter for defect with id=%s not found", defectId));
        }
        return measuredParameters;
    }

    private CalculationType getCalculationType(String calculation) {
        return CalculationType.from(calculation).orElseThrow(
                () -> new BadRequestException(
                        String.format("Unsupported measured parameter calculation type=%s", calculation)));
    }
}