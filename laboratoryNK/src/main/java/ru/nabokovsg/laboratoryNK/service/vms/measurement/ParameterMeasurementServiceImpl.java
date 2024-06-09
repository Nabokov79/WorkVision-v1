package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.mapper.vms.measurement.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameterType;
import ru.nabokovsg.laboratoryNK.repository.vms.measurement.ParameterMeasurementServiceRepository;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstParameterMeasurementService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParameterMeasurementServiceImpl implements ParameterMeasurementService {

    private final ParameterMeasurementServiceRepository repository;
    private final ParameterMeasurementMapper mapper;
    private final CalculationParameterMeasurementService calculationService;
    private final ConstParameterMeasurementService parameterMeasurementService;

    @Override
    public Set<CalculationParameterMeasurement> saveForDefectMeasurement(Defect defect
                                                            , DefectMeasurement defectMeasurement
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Set<CalculationParameterMeasurement> calculations = setSequentialNumberParameters(
                calculationService.calculation(defect.getTypeCalculation()
                                             , defect.getMeasuredParameters()
                                             , defectMeasurement.getParameterMeasurements()
                                             , parameterMeasurementsDto));
        return new HashSet<>(repository.saveAll(calculations.stream()
                .map(p -> mapper.mapWithDefectMeasurement(p, defectMeasurement))
                .collect(Collectors.toSet())));
    }

    @Override
    public Set<CalculationParameterMeasurement> saveForCompletedRepair(ElementRepair elementRepair
                                                            , CompletedRepairElement completedRepair
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Set<CalculationParameterMeasurement> calculations = setSequentialNumberParameters(
                calculationService.calculation(elementRepair.getTypeCalculation()
                                             , elementRepair.getMeasuredParameters()
                                             , completedRepair.getParameterMeasurements()
                                             , parameterMeasurementsDto));
        return new HashSet<>(repository.saveAll(calculations.stream()
                .map(p -> mapper.mapWithCompletedRepairElement(p, completedRepair))
                .collect(Collectors.toSet())));
    }

    private Set<CalculationParameterMeasurement> setSequentialNumberParameters(
                                                          Set<CalculationParameterMeasurement> parameterMeasurements) {
        int sequentialNumber = 1;
        int number = 1;
        int size = parameterMeasurements.size();
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
                if (parameter.getNumber() != null) {
                    if (number == parameter.getNumber()) {
                        number += 1;
                    }
                    if (sequentialNumber < parameter.getSequentialNumber()
                                                               || sequentialNumber == parameter.getSequentialNumber()) {
                        sequentialNumber = parameter.getSequentialNumber() + 1;
                    }
                }
            }
        String squareName = parameterMeasurementService.get(String.valueOf(MeasuredParameterType.SQUARE));
        String quantityName = parameterMeasurementService.get(String.valueOf(MeasuredParameterType.QUANTITY));
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getNumber() == null) {
                if (parameter.getParameterName().equals(squareName)) {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(sequentialNumber);
                    sequentialNumber += 1;
                } else if (parameter.getParameterName().equals(quantityName)) {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(size);
                } else {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(sequentialNumber);
                    sequentialNumber += 1;
                }

            }
        }
        return parameterMeasurements;
    }
}