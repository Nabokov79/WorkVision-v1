package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;

import java.util.Set;

public interface CalculationParameterMeasurementBuilderService {

    Set<CalculationParameterMeasurement> create(CalculationType type
                                              , Set<MeasuredParameter> measuredParameters
                                              , Double firstValue, Double secondValue);
}