package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationParameterMeasurementServiceImpl extends ConstParameterMeasurement
        implements CalculationParameterMeasurementService {

    private final ParameterMeasurementMapper mapper;

    @Override
    public void calculation(Defect defect, DefectMeasurement defectMeasurement, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Map<Long, MeasuredParameter> measuredParameters = defect.getMeasuredParameters().stream().collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
        log.info("START calculation parameterMeasurements = {}", defectMeasurement.getParameterMeasurements());
        if (defectMeasurement.getParameterMeasurements() == null || defectMeasurement.getParameterMeasurements().isEmpty()) {
            defectMeasurement.setParameterMeasurements(new HashSet<>());
            defect.getMeasuredParameters().forEach(v -> defectMeasurement.getParameterMeasurements().add(mapper.mapToNewParameterMeasurement(v)));
            log.info("After add MeasuredParameters in parameterMeasurements = {}", defectMeasurement.getParameterMeasurements());
        }
        log.info("RESULT parameterMeasurements = {}",defectMeasurement.getParameterMeasurements());
        Map<Long, ParameterMeasurementDto> parameterMeasurementDto = parameterMeasurementsDto.stream().collect(Collectors.toMap(ParameterMeasurementDto::getParameterId, p -> p));
        Map<String, ParameterMeasurementDto> parametersDto = defect.getMeasuredParameters().stream().collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> parameterMeasurementDto.get(m.getId())));
        defectMeasurement.getParameterMeasurements().forEach(p -> {
            if (parametersDto.get(p.getParameterName()) != null) {
                switch (measuredParameters.get(parametersDto.get(p.getParameterName()).getParameterId()).getTypeCalculation()) {
                    case MIN -> countMin(p, parametersDto.get(p.getParameterName()));
                    case MAX -> countMax(p, parametersDto.get(p.getParameterName()));
                    case MAX_MIN -> countMax(countMin(p, parametersDto.get(p.getParameterName())), parametersDto.get(p.getParameterName()));
                }
            }
        });
        switch (defect.getTypeCalculation()) {
            case SQUARE -> countSquare(measuredParameters, defectMeasurement.getParameterMeasurements(), parameterMeasurementsDto);
            case QUANTITY -> countQuantity(measuredParameters.values().stream().collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> m)).get(getQuantity())
                                         , defectMeasurement.getParameterMeasurements()
                                         , parameterMeasurementsDto);
        }
    }


    private CalculationParameterMeasurement countMin(CalculationParameterMeasurement parameterMeasurement
            , ParameterMeasurementDto parameterMeasurementDto) {
        if (parameterMeasurementDto.getValue() == null) {
            throw new NotFoundException(
                    String.format("To calculate the minimum" +
                            ", the measured value must not be zero, measurement=%s", parameterMeasurementDto.getValue())
            );
        }
        if (parameterMeasurement.getFirstValue() == null
                || parameterMeasurementDto.getValue() > parameterMeasurement.getFirstValue()) {
            parameterMeasurement.setFirstValue(parameterMeasurementDto.getValue());
        }
        return parameterMeasurement;
    }

    private void countMax(CalculationParameterMeasurement parameterMeasurement
            , ParameterMeasurementDto parameterMeasurementDto) {
        if (parameterMeasurementDto.getValue() == null) {
            throw new NotFoundException(
                    String.format("To calculate the minimum" +
                            ", the measured value must not be zero, measurement=%s", parameterMeasurementDto.getValue())
            );
        }
        if (parameterMeasurement.getSecondValue() == null
                || parameterMeasurement.getSecondValue() < parameterMeasurementDto.getValue()) {
            parameterMeasurement.setSecondValue(parameterMeasurementDto.getValue());
        }
    }

    private void countQuantity(MeasuredParameter measuredParameter, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        double value = 1.0;
        boolean flag = true;
        if (measuredParameter != null) {
            value = parameterMeasurementsDto.stream()
                                            .collect(Collectors.toMap(ParameterMeasurementDto::getParameterId, p -> p))
                                            .get(measuredParameter.getId())
                                            .getValue();
        }
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getParameterName().equals(getQuantity())) {
                if (parameter.getFirstValue() == null) {
                    parameter.setFirstValue(value);
                } else {
                    parameter.setFirstValue(parameter.getFirstValue() + value);
                }
                flag = false;
            }
        }
        if (flag && measuredParameter == null) {
            parameterMeasurements.add(mapper.mapToShortCalculationParameter(getQuantity(), value, getPieces()));
        }
    }

    private void countSquare(Map<Long, MeasuredParameter> measuredParameters, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parametersDto) {
        CalculationParameterMeasurement parameterMeasurement = parameterMeasurements
                .stream()
                .collect(Collectors.toMap(CalculationParameterMeasurement::getParameterName, c -> c))
                .get(getSquare());
        Map<String, ParameterMeasurementDto> parameterMeasurementsDto = parametersDto.stream().collect(Collectors.toMap(p -> measuredParameters.get(p.getParameterId()).getParameterName(), p -> p));
        CalculationParameterMeasurement square = mapper.mapToShortCalculationParameter(getSquare(), countSquareByLengthAndWidth(parameterMeasurementsDto), getPieces());
        if (parameterMeasurementsDto.get(getDiameter()) != null && square.getFirstValue() == 0) {
            square.setFirstValue(countSquareByDiameter(parameterMeasurementsDto.get(getDiameter()).getValue()));
        }
        if (square.getFirstValue() == 0) {
            square.setFirstValue(countSquareByLengthAndHeight(parameterMeasurementsDto));
        }
        if (parameterMeasurement != null && parameterMeasurement.getUnitMeasurement().equals(getM2())) {
            square.setFirstValue(square.getFirstValue() / 1000000);
        }
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getParameterName().equals(getSquare())) {
                if (Objects.equals(square.getFirstValue(), parameterMeasurement.getFirstValue())) {
                    parameter.setFirstValue(parameter.getFirstValue() + 1.0);
                } else {
                    parameterMeasurements.add(square);
                }
            }
        }
    }

    private double countSquareByLengthAndWidth(Map<String, ParameterMeasurementDto> parameterMeasurementsDto) {
        Double length = parameterMeasurementsDto.get(getLength()).getValue();
        Double width = parameterMeasurementsDto.get(getWidth()).getValue();
        if (length != null && width != null) {
            return length * width;
        }
        return 0;
    }

    private double countSquareByLengthAndHeight(Map<String, ParameterMeasurementDto> parameterMeasurementsDto) {
        Double length = parameterMeasurementsDto.get(getLength()).getValue();
        Double height = parameterMeasurementsDto.get(getHeight()).getValue();
        if (length != null && height != null) {
            return length * height;
        }
        return 0;
    }

    private double countSquareByDiameter(double diameter) {
        double rad = diameter / 2;
        return Math.PI * rad * rad * 100 / 100;
    }
}