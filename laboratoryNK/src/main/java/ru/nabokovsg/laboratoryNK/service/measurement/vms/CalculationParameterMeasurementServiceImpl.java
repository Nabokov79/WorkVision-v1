package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class CalculationParameterMeasurementServiceImpl extends ConstParameterMeasurement
                                                        implements CalculationParameterMeasurementService {

    @Override
    public CalculationParameterMeasurement countMin(CalculationParameterMeasurement parameterMeasurement
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

    @Override
    public CalculationParameterMeasurement countMax(CalculationParameterMeasurement parameterMeasurement
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
        return parameterMeasurement;
    }

    @Override
    public CalculationParameterMeasurement countMaxAndMin(CalculationParameterMeasurement parameterMeasurement
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
        } else {
            parameterMeasurement.setFirstValue(parameterMeasurementDto.getValue());
        }
        return parameterMeasurement;
    }

    @Override
    public Set<CalculationParameterMeasurement> countQuantity(
                                                      Map<Long, CalculationParameterMeasurement> parameterMeasurements
                                                    , Map<String, ParameterMeasurementDto> parameterMeasurementsDto) {
        Double firstValue = parameterMeasurementsDto.get(getQuantity()).getValue();
        CalculationParameterMeasurement quantity = parameterMeasurements.get(parameterMeasurementsDto.get(getQuantity()).getParameterId());
        if (quantity == null) {
            quantity = new CalculationParameterMeasurement(null, getQuantity(), null, null, getPieces(), null, null);
        }
        if (quantity.getFirstValue() == null && firstValue == null) {
            quantity.setFirstValue(1.0);
        } else {
            if (firstValue != null) {
                quantity.setFirstValue(quantity.getFirstValue() + firstValue);
            } else {
                quantity.setFirstValue(quantity.getFirstValue() + 1.0);
            }
        }
        parameterMeasurements.put(quantity.getId(), quantity);
        return new HashSet<>(parameterMeasurements.values());
    }

    @Override
    public Set<CalculationParameterMeasurement> countSquare(
                                                      Map<Long, CalculationParameterMeasurement> parameterMeasurements
                                                    , Map<String, ParameterMeasurementDto> parameterMeasurementsDto) {
        CalculationParameterMeasurement parameterMeasurement
                                = parameterMeasurements.get(parameterMeasurementsDto.get(getSquare()).getParameterId());
        Double square = parameterMeasurementsDto.get(getSquare()).getValue();
        if (square == null) {
            if (parameterMeasurementsDto.get(getLength()) != null) {
                if (parameterMeasurementsDto.get(getWidth()) != null) {
                    square = parameterMeasurementsDto.get(getLength()).getValue()
                           * parameterMeasurementsDto.get(getWidth()).getValue();
                }
                if (parameterMeasurementsDto.get(getHeight()) != null
                                                                 && parameterMeasurementsDto.get(getWidth()) == null) {
                    square = parameterMeasurementsDto.get(getLength()).getValue()
                           * parameterMeasurementsDto.get(getHeight()).getValue();
                }
            }
            if (parameterMeasurementsDto.get(getDiameter()) != null) {
                double rad = parameterMeasurementsDto.get(getDiameter()).getValue() / 2;
                if (parameterMeasurementsDto.get(getHeight()) != null) {
                    square = 2 * Math.PI * rad * parameterMeasurementsDto.get(getHeight()).getValue() * 100 / 100;
                } else {
                    square = Math.PI * rad * rad * 100 / 100;
                }
            }
            if (square != null && parameterMeasurement.getUnitMeasurement().equals(getM2())) {
                square /= 1000000;
            }
            parameterMeasurement.setFirstValue(square);
            parameterMeasurements.put(parameterMeasurement.getId(), parameterMeasurement);
            if (Objects.equals(square, parameterMeasurement.getFirstValue())) {
                return countQuantity(parameterMeasurements, parameterMeasurementsDto);
            }
        }
        return new HashSet<>(parameterMeasurements.values());
    }
 }