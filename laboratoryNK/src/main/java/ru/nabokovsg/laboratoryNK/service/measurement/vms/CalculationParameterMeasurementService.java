package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.CalculationType;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

import java.util.List;
import java.util.Set;

public interface CalculationParameterMeasurementService {

    Set<CalculationParameterMeasurement> calculation(CalculationType typeCalculation, Set<MeasuredParameter> measuredParameters, Set<CalculationParameterMeasurement> parameterMeasurements, List<ParameterMeasurementDto> parameterMeasurementsDto);
}