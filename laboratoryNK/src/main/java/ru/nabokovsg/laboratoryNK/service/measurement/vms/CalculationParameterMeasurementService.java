package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;

import java.util.Map;
import java.util.Set;

public interface CalculationParameterMeasurementService {

    CalculationParameterMeasurement countMin(CalculationParameterMeasurement parameterMeasurement
                                           , ParameterMeasurementDto parameterMeasurementDto);

    CalculationParameterMeasurement countMax(CalculationParameterMeasurement parameterMeasurement
                                           , ParameterMeasurementDto parameterMeasurementDto);

    CalculationParameterMeasurement countMaxAndMin(CalculationParameterMeasurement parameterMeasurement
                                                 , ParameterMeasurementDto parameterMeasurementDto);

    Set<CalculationParameterMeasurement> countSquare(Map<Long, CalculationParameterMeasurement> parameterMeasurements
                                                   , Map<String, ParameterMeasurementDto> parameterMeasurementsDto);

   Set<CalculationParameterMeasurement> countQuantity(Map<Long, CalculationParameterMeasurement> parameterMeasurements
                                                    , Map<String, ParameterMeasurementDto> parameterMeasurementsDto);
}