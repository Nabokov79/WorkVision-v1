package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.CalculationParameterMeasurementBuilder;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
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
    public Set<CalculationParameterMeasurement> save(CalculationParameterMeasurementBuilder builder) {
        Set<CalculationParameterMeasurement> calculations;
        if (builder.getDefect() != null) {
            calculations = setNumbers(builder)
                    .stream()
                    .map(p -> mapper.mapWithDefectMeasurement(p, builder.getDefect()))
                    .collect(Collectors.toSet());
        } else if (builder.getRepair() != null) {
            calculations = setNumbers(builder)
                    .stream()
                    .map(p -> mapper.mapWithCompletedRepairElement(p, builder.getRepair()))
                    .collect(Collectors.toSet());
        } else {
            throw new BadRequestException(
                    String.format("DefectMeasurement=%s and CompletedRepairElement=%s should not be null"
                            , builder.getDefect(), builder.getRepair()));
        }
        return new HashSet<>(repository.saveAll(calculations));
    }

    private Set<CalculationParameterMeasurement> setNumbers(CalculationParameterMeasurementBuilder builder) {
        Set<CalculationParameterMeasurement> calculations = calculationService.calculation(builder);
        int sequentialNumber = 1;
        int number = 1;
        int size = calculations.size();
        for (CalculationParameterMeasurement measurement : calculations) {
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
        return calculations;
    }
}