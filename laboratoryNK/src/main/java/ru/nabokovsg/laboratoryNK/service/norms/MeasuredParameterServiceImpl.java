package ru.nabokovsg.laboratoryNK.service.norms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.norms.measuredParameter.MeasuredParameterDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.norms.MeasuredParameterMapper;
import ru.nabokovsg.laboratoryNK.model.norms.*;
import ru.nabokovsg.laboratoryNK.repository.norms.MeasuredParameterRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MeasuredParameterServiceImpl implements MeasuredParameterService {

    private final MeasuredParameterRepository repository;
    private final MeasuredParameterMapper mapper;
    private final ConstParameterMeasurementService constService;

    @Override
    public Set<MeasuredParameter> saveForDefect(Defect defect, List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(parametersDto.stream()
                .map(p -> mapper.mapForDefect(
                          constService.getMeasuredParameter(p.getMeasuredParameter())
                        , constService.getUnitMeasurement(p.getUnitMeasurement())
                        , defect
                        , getTypeOfParameterCalculation(p.getTypeCalculation())))
                .toList()));
    }

    @Override
    public Set<MeasuredParameter> saveForElementRepair(ElementRepair repair, List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(
                parametersDto.stream()
                        .map(p -> mapper.mapForElementRepair(
                                  constService.getMeasuredParameter(p.getMeasuredParameter())
                                , constService.getUnitMeasurement(p.getUnitMeasurement())
                                , repair
                                , getTypeOfParameterCalculation(p.getTypeCalculation())))
                        .toList())
        );
    }

    @Override
    public Set<MeasuredParameter> update(List<MeasuredParameterDto> parametersDto) {
        return new HashSet<>(repository.saveAll(
                parametersDto.stream()
                        .map(p -> mapper. mapToUpdateMeasuredParameter(p.getId()
                                , constService.getMeasuredParameter(p.getMeasuredParameter())
                                , constService.getUnitMeasurement(p.getUnitMeasurement())))
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

    private TypeOfParameterCalculation getTypeOfParameterCalculation(String calculation) {
        return TypeOfParameterCalculation.from(calculation).orElseThrow(
                () -> new BadRequestException(
                        String.format("Unsupported measured parameter calculation type=%s", calculation)));
    }
}