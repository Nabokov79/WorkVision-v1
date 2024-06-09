package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameterType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.UnitMeasurementType;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstDefectDirectionService;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstParameterMeasurementService;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstUnitMeasurementService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationParameterMeasurementServiceImpl implements CalculationParameterMeasurementService {

    private final CalculationParameterMeasurementBuilderService builderService;
    private final ConstParameterMeasurementService parameterMeasurementService;
    private final ConstUnitMeasurementService unitMeasurementService;
    private final ConstDefectDirectionService directionService;

    @Override
    public Set<CalculationParameterMeasurement> calculation(CalculationType typeCalculation
                                                          , Set<MeasuredParameter> measuredParameters
                                                          , Set<CalculationParameterMeasurement> parameterMeasurements
                                                          , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Map<Long, MeasuredParameter> parameters = measuredParameters.stream()
                                                           .collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
        if (parameterMeasurements == null || parameterMeasurements.isEmpty()) {
            parameterMeasurements = builderService.create(typeCalculation, measuredParameters, null, null);
        }
        switch (typeCalculation) {
            case SQUARE -> countSquare(parameters, parameterMeasurements, parameterMeasurementsDto);
            case QUANTITY -> {
                String parameterName = parameterMeasurementService.get(String.valueOf(MeasuredParameterType.QUANTITY));
                for (CalculationParameterMeasurement parameter : parameterMeasurements) {
                    if (parameter.getParameterName().equals(parameterName)) {
                        countQuantity(measuredParameters.stream()
                                        .collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> m))
                                        .get(parameterName)
                                , parameter
                                , parameterMeasurementsDto);
                    }
                }
            }
        }
        Map<String, CalculationType> types = measuredParameters.stream()
                                                    .collect(Collectors.toMap(MeasuredParameter::getParameterName
                                                                            , MeasuredParameter::getTypeCalculation));
        Map<String, ParameterMeasurementDto> parametersDto = parameterMeasurementsDto.stream()
                .collect(Collectors.toMap(p -> parameters.get(p.getParameterId()).getParameterName()
                                        , parameter -> parameter));
        parameterMeasurements.forEach(p -> {
                    CalculationType type = types.get(p.getParameterName());
                    if (type != null) {
                        switch (type) {
                            case MIN -> setMin(p, parametersDto.get(p.getParameterName()));
                            case MAX -> setMax(p, parametersDto.get(p.getParameterName()));
                            case MAX_MIN -> {
                                setMin(p, parametersDto.get(p.getParameterName()));
                                setMax(p, parametersDto.get(p.getParameterName()));
                            }
                            case DIRECTION -> setDirection(p, parametersDto.get(p.getParameterName()));
                            case NO_ACTION -> p.setFirstValue(parametersDto.get(p.getParameterName()).getValue());
                        }
                    }
                }
        );
        return parameterMeasurements;
    }

    private void setDirection(CalculationParameterMeasurement parameter, ParameterMeasurementDto parameterDto) {
        parameter.setDirection(directionService.get(parameterDto.getDirection()));
    }

    private void setMin(CalculationParameterMeasurement parameter, ParameterMeasurementDto parameterDto) {
        if (parameter.getFirstValue() == null || parameterDto.getValue() < parameter.getFirstValue()) {
            parameter.setFirstValue(parameterDto.getValue());
        }
    }

    private void setMax(CalculationParameterMeasurement parameter, ParameterMeasurementDto parameterDto) {
        if (parameter.getSecondValue() == null || parameterDto.getValue() > parameter.getSecondValue()) {
            parameter.setSecondValue(parameterDto.getValue());
        }
    }

    private void countQuantity(MeasuredParameter measuredParameter
                             , CalculationParameterMeasurement parameter
                             , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        double value = 1.0;
        if (measuredParameter != null) {
            value = parameterMeasurementsDto.stream()
                    .collect(Collectors.toMap(ParameterMeasurementDto::getParameterId, p -> p))
                    .get(measuredParameter.getId())
                    .getValue();
        }
        setQuantity(parameter, value);
    }

    private void setQuantity(CalculationParameterMeasurement parameter, double value) {
        if (parameter.getFirstValue() == null) {
            parameter.setFirstValue(value);
        } else {
            parameter.setFirstValue(parameter.getFirstValue() + value);
        }
    }

    private void countSquare(Map<Long, MeasuredParameter> measuredParameters
                           , Set<CalculationParameterMeasurement> parameterMeasurements
                           , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        double length = 0;
        double width = 0;
        double diameter = 0;
        double square = 0;
        double quantity = 1.0;
        boolean flag = true;
        String unitMeasurement = unitMeasurementService.get(String.valueOf(UnitMeasurementType.MM));
        for (ParameterMeasurementDto parameter : parameterMeasurementsDto) {
            MeasuredParameter measuredParameter = measuredParameters.get(parameter.getParameterId());
            if (parameter.getValue() != null) {
                if (measuredParameter.getParameterName().equals(
                                     parameterMeasurementService.get(String.valueOf(MeasuredParameterType.DIAMETER)))) {
                    diameter = parameter.getValue();
                }
                if (measuredParameter.getParameterName().equals(
                                       parameterMeasurementService.get(String.valueOf(MeasuredParameterType.LENGTH)))) {
                    length = parameter.getValue();
                }
                if (measuredParameter.getParameterName().equals(
                                        parameterMeasurementService.get(String.valueOf(MeasuredParameterType.WIDTH)))) {
                    width = parameter.getValue();
                }
            }
            if (!measuredParameter.getUnitMeasurement().equals(unitMeasurement)) {
                unitMeasurement = measuredParameter.getUnitMeasurement();
            }
        }
        if (length != 0 && width != 0) {
            square = countSquareRectangle(length, width);
        }
        if (diameter != 0) {
            square = countSquareCircle(diameter);
        }
        if (unitMeasurement.equals(unitMeasurementService.get(String.valueOf(UnitMeasurementType.MM)))) {
            square /= 1000000;
        }
        String squareName = parameterMeasurementService.get(String.valueOf(MeasuredParameterType.SQUARE));
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getParameterName().equals(squareName)) {
                if (parameter.getFirstValue() == null) {
                    parameter.setFirstValue(square);
                }
                if (parameter.getFirstValue() == square) {
                    String quantityName = parameterMeasurementService.get(String.valueOf(MeasuredParameterType.QUANTITY));
                    for (CalculationParameterMeasurement p : parameterMeasurements) {
                        if (p.getParameterName().equals(quantityName) && Objects.equals(p.getNumber(), parameter.getNumber())) {
                            setQuantity(p, quantity);
                        }
                    }
                    flag = false;
                }
            }
        }
        if (flag) {
            parameterMeasurements.addAll(
                    builderService.create(CalculationType.SQUARE
                                        , new HashSet<>(measuredParameters.values())
                                        , square
                                        , quantity)
            );
        }
    }

    private double countSquareRectangle(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    private double countSquareCircle(double diameter) {
        double rad = diameter / 2;
        return Math.PI * rad * rad * 100 / 100;
    }
}