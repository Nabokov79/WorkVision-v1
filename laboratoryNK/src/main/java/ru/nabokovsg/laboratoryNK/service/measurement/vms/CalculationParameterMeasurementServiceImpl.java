package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.CalculationType;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationParameterMeasurementServiceImpl extends ConstParameterMeasurement
                                                        implements CalculationParameterMeasurementService {

    private final ParameterMeasurementMapper mapper;

    @Override
    public Set<CalculationParameterMeasurement> calculation(CalculationType typeCalculation, Set<MeasuredParameter> measuredParameters, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        log.info("Start Calculation: {}", parameterMeasurements);
        Map<Long, MeasuredParameter> parameters = measuredParameters.stream().collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
        Map<String, ParameterMeasurementDto> parametersDto = parameterMeasurementsDto.stream().collect(Collectors.toMap(p -> parameters.get(p.getParameterId()).getParameterName(), parameter -> parameter));
        parameterMeasurements.forEach(p ->
                {
                    ParameterMeasurementDto parameter = parametersDto.get(p.getParameterName());
                    if (parameter != null) {
                        countParameter(parameters.get(parameter.getParameterId()).getTypeCalculation()
                                , p
                                , parametersDto.get(p.getParameterName()).getValue());
                    }
                }
        );
        if (!typeCalculation.equals(CalculationType.NO_ACTION)) {
            countDefectOrCompletedRepair(typeCalculation, parameters, parameterMeasurements, parameterMeasurementsDto);
        }
        return parameterMeasurements;
    }

    private void countDefectOrCompletedRepair(CalculationType typeCalculation, Map<Long, MeasuredParameter> measuredParameters, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Map<Long, ParameterMeasurementDto> parameterMeasurementDto = parameterMeasurementsDto.stream()
                .collect(Collectors.toMap(ParameterMeasurementDto::getParameterId, p -> p));
        Map<String, ParameterMeasurementDto> parametersDto = measuredParameters.values().stream()
                .collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> parameterMeasurementDto.get(m.getId())));
        switch (typeCalculation) {
            case SQUARE -> countSquare(measuredParameters, parameterMeasurements, parametersDto);
            case QUANTITY ->
                    countQuantity(measuredParameters.values().stream().collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> m)).get(getQuantity())
                            , parameterMeasurements
                            , parameterMeasurementsDto);
        }
    }

    private void countParameter(CalculationType typeCalculation, CalculationParameterMeasurement parameter, Double value) {
        if (value == null) {
            throw new NotFoundException(
                    String.format("To calculate, the measured value must not be zero, value=%s", value)
            );
        }
        switch (typeCalculation) {
            case MIN -> {
                if (parameter.getFirstValue() == null || value < parameter.getFirstValue()) {
                    parameter.setFirstValue(value);
                }
            }
            case MAX -> {
                if (parameter.getSecondValue() == null || value > parameter.getSecondValue()) {
                    parameter.setSecondValue(value);
                }
            }
            case MAX_MIN -> {
                if (parameter.getFirstValue() == null || value < parameter.getFirstValue()) {
                    parameter.setFirstValue(value);
                }
                if (parameter.getSecondValue() == null || value > parameter.getSecondValue()) {
                    parameter.setSecondValue(value);
                }
            }
            case NO_ACTION -> parameter.setFirstValue(value);
        }
    }

    private void countQuantity(MeasuredParameter measuredParameter, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        double value = 1.0;
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
            }
        }
    }

    private void countSquare(Map<Long, MeasuredParameter> measuredParameters
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , Map<String, ParameterMeasurementDto> parametersDto) {
        log.info("START count Square:");
        log.info("parameterMeasurements = {}", parameterMeasurements);
        log.info("parametersDto = {}", parametersDto);
        double length = 0;
        double width = 0;
        double height = 0;
        double diameter = 0;
        double square = 0;
        double quantity = 1.0;
        for (ParameterMeasurementDto parameter : parametersDto.values()) {
            String parameterName = measuredParameters.get(parameter.getParameterId()).getParameterName();
            if (parameterName.equals(getLength())) {
                length = parameter.getValue() / 1000;
            } else if (parameterName.equals(getWidth())) {
                width = parameter.getValue() / 1000;
            } else if (parameterName.equals(getHeight())) {
                height = parameter.getValue();
            } else if (parameterName.equals(getDiameter())) {
                diameter = parameter.getValue() / 1000;
            }
        }
        log.info("Input data:");
        log.info("length = {}", length);
        log.info("width = {}", width);
        log.info("height = {}", height);
        log.info("diameter = {}", diameter);
        log.info("square = {}", square);
        log.info("quantity = {}", quantity);
        log.info("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        if (length != 0 && width != 0) {
            log.info("count by length and width :");
            square = length * width;
            log.info("square = {}", square);
        }
        if (diameter != 0) {
            log.info("count by diameter :");
            double rad = diameter / 2;
            square = Math.PI * rad * rad * 100 / 100;
            log.info("square = {}", square);
        }
        log.info("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getParameterName().equals(getSquare())) {
                if (parameter.getFirstValue() == null) {
                    parameter.setFirstValue(square);
                }
                if (parameter.getFirstValue() == square) {
                    log.info("Start set quantity square = {}", square);
                    for (CalculationParameterMeasurement p : parameterMeasurements) {
                        log.info("ParameterName = {}", p.getParameterName());
                        log.info("p.getNumber() = {}", p.getNumber());
                        log.info("parameter.getNumber() = {}", parameter.getNumber());
                        if (p.getParameterName().equals(getQuantity())) {
                            if (p.getFirstValue() == null) {
                                log.info("NULL value = {}", p.getFirstValue());
                                p.setFirstValue(quantity);
                            } else {
                                p.setFirstValue(p.getFirstValue() + quantity);
                                log.info("NOT NULL value = {}", p.getFirstValue());
                            }
                            return;
                        }
                    }
                } else {
                    parameterMeasurements.add(mapper.mapToShortCalculationParameter(getSquare(), square, getM2()));
                    parameterMeasurements.add(mapper.mapToShortCalculationParameter(getQuantity(), quantity, getPieces()));
                }
            }
            log.info("END count Square:");
            log.info("parameterMeasurements = {}", parameterMeasurements);
        }
    }
}