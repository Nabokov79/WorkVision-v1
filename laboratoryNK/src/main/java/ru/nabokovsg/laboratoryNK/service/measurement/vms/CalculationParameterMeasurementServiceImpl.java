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
import ru.nabokovsg.laboratoryNK.model.norms.CalculationType;
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
        Map<Long, ParameterMeasurementDto> parameterMeasurementDto = parameterMeasurementsDto.stream()
                                            .collect(Collectors.toMap(ParameterMeasurementDto::getParameterId, p -> p));
        Map<String, ParameterMeasurementDto> parametersDto = defect.getMeasuredParameters().stream()
                .collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> parameterMeasurementDto.get(m.getId())));
        switch (defect.getTypeCalculation()) {
            case SQUARE -> {
                setParameterMeasurements(defect, defectMeasurement);
                countParameters(defectMeasurement, measuredParameters, parametersDto);
                countSquare(measuredParameters, defectMeasurement.getParameterMeasurements(), parametersDto);
            }
            case QUANTITY ->
                    countQuantity(measuredParameters.values().stream().collect(Collectors.toMap(MeasuredParameter::getParameterName, m -> m)).get(getQuantity())
                            , defectMeasurement.getParameterMeasurements()
                            , parameterMeasurementsDto);
        }
    }

    private void setParameterMeasurements(Defect defect, DefectMeasurement defectMeasurement) {
        if (defectMeasurement.getParameterMeasurements() == null) {
            defectMeasurement.setParameterMeasurements(new HashSet<>());
            defect.getMeasuredParameters().forEach(v -> defectMeasurement.getParameterMeasurements().add(mapper.mapToNewParameterMeasurement(v)));
        }
    }

    private void countParameters(DefectMeasurement defectMeasurement, Map<Long, MeasuredParameter> measuredParameters,  Map<String, ParameterMeasurementDto> parametersDto) {
        defectMeasurement.getParameterMeasurements().forEach(p -> {
            if (parametersDto.get(p.getParameterName()).getValue() == null) {
                throw new NotFoundException(
                        String.format("To calculate, the measured value must not be zero, value=%s", parametersDto.get(p.getParameterName()).getValue())
                );
            }
            switch (measuredParameters.get(parametersDto.get(p.getParameterName()).getParameterId()).getTypeCalculation()) {
                case MIN -> {
                    if (p.getFirstValue() == null || parametersDto.get(p.getParameterName()).getValue() < p.getFirstValue()) {
                        p.setFirstValue(parametersDto.get(p.getParameterName()).getValue());
                    }
                }
                case MAX -> {
                    if (p.getSecondValue() == null || parametersDto.get(p.getParameterName()).getValue() > p.getSecondValue()) {
                        p.setSecondValue(parametersDto.get(p.getParameterName()).getValue());
                    }
                }
                case MAX_MIN -> {
                    if (p.getFirstValue() == null || parametersDto.get(p.getParameterName()).getValue() < p.getFirstValue()) {
                        p.setFirstValue(parametersDto.get(p.getParameterName()).getValue());
                    }
                    if (p.getSecondValue() == null || parametersDto.get(p.getParameterName()).getValue() > p.getSecondValue()) {
                        p.setSecondValue(parametersDto.get(p.getParameterName()).getValue());
                    }
                }
            }
        });
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
                length = parameter.getValue();
            } else if (parameterName.equals(getWidth())) {
                width = parameter.getValue();
            } else if (parameterName.equals(getHeight())) {
                height = parameter.getValue();
            } else if (parameterName.equals(getDiameter())) {
                diameter = parameter.getValue();
            }
        }
        log.info("Input data:");
        log.info("length = {}", length);
        log.info("width = {}", width);
        log.info("height = {}", height);
        log.info("diameter = {}", diameter);
        log.info("square = {}", square);
        log.info("quantity = {}", quantity);
        if (length != 0 && width != 0) {
            log.info("count by length and width :");
            square = length * width;
            log.info("square = {}", square);
        }
        log.info("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        if (diameter != 0) {
            log.info("count by diameter :");
            double rad = diameter / 2;
            square = Math.PI * rad * rad * 100 / 100;
            log.info("square = {}", square);
        }
        square = square / 1000000;
        log.info("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        if (parameterMeasurements == null) {
            parameterMeasurements = new HashSet<>();
            parameterMeasurements.add(mapper.mapToShortCalculationParameter(getSquare(), square, getM2()));
            parameterMeasurements.add(mapper.mapToShortCalculationParameter(getHeight(), height, getMm()));
            parameterMeasurements.add(mapper.mapToShortCalculationParameter(getQuantity(), quantity, getPieces()));
            log.info("parameterMeasurements = NULL");
            log.info("parameterMeasurements = {}", parameterMeasurements);
        } else {
            Map<String, CalculationType> typeCalculations = measuredParameters.values()
                                                      .stream()
                                                      .collect(Collectors.toMap(MeasuredParameter::getParameterName
                                                                              , MeasuredParameter::getTypeCalculation));
            for (CalculationParameterMeasurement parameter : parameterMeasurements) {
                if (parameter.getParameterName().equals(getSquare())) {
                    if (square == parameter.getFirstValue()) {
                        parameter.setFirstValue(square);
                    }
                }
            }
        }
        log.info("END count Square:");
        log.info("parameterMeasurements = {}", parameterMeasurements);
    }
}