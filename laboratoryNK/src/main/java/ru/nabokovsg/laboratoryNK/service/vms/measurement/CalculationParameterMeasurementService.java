package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;

import java.util.List;
import java.util.Set;

public interface CalculationParameterMeasurementService {

    Set<CalculationParameterMeasurement> calculation(CalculationType typeCalculation
                                                   , Set<MeasuredParameter> measuredParameters
                                                   , Set<CalculationParameterMeasurement> parameterMeasurements
                                                   , List<ParameterMeasurementDto> parameterMeasurementsDto);
}