package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.ParameterMeasurementServiceRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParameterMeasurementServiceImpl extends ConstParameterMeasurement implements ParameterMeasurementService {

    private final ParameterMeasurementServiceRepository repository;
    private final ParameterMeasurementMapper mapper;
    private final CalculationParameterMeasurementService calculationService;

    @Override
    public Set<CalculationParameterMeasurement> saveDefectMeasurement(Defect defect, DefectMeasurement defectMeasurement, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        calculationService.calculation(defect, defectMeasurement, parameterMeasurementsDto);
        Set<CalculationParameterMeasurement> calculations =defectMeasurement.getParameterMeasurements().stream().map(p -> mapper.mapWithDefectMeasurement(p, defectMeasurement)).collect(Collectors.toSet());
        setNumbers(calculations);
        return new HashSet<>(repository.saveAll(calculations));
    }

    @Override
    public Set<CalculationParameterMeasurement> saveCompletedRepairElement(ElementRepair elementRepair, CompletedRepairElement repair, List<ParameterMeasurementDto> parameterMeasurementsDto) {
        return null;
    }

    private void setNumbers(Set<CalculationParameterMeasurement> parameterMeasurements) {
        int sequentialNumber = 1;
        int number = 1;
        int size = parameterMeasurements.size();
        for (CalculationParameterMeasurement measurement : parameterMeasurements) {
            if (measurement.getNumber() == null) {
                measurement.setNumber(number);
                if (measurement.getParameterName().equals(getQuantity())) {
                    measurement.setSequentialNumber(size);
                } else if (measurement.getParameterName().equals(getSquare())) {
                    measurement.setSequentialNumber(1);
                } else {
                    measurement.setSequentialNumber(sequentialNumber);
                    sequentialNumber += 1;
                }
            }
        }
    }
}