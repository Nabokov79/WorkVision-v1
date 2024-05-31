package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CalculationParameterMeasurementService {

    Set<CalculationParameterMeasurement> calculationDefectOrRepair(TypeCalculation typeCalculation
            , Map<Long, CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto);

    CalculationParameterMeasurement calculationParameterMeasurement(MeasuredParameter measuredParameter
                                                                  , CalculationParameterMeasurement parameterMeasurement
                                                                  , ParameterMeasurementDto parameterMeasurementDto);
}