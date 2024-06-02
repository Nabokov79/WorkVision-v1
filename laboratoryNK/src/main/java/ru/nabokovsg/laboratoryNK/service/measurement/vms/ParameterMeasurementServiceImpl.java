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

@Service
@RequiredArgsConstructor
@Slf4j
public class ParameterMeasurementServiceImpl extends ConstParameterMeasurement implements ParameterMeasurementService {

    private final ParameterMeasurementServiceRepository repository;
    private final ParameterMeasurementMapper mapper;
    private final CalculationParameterMeasurementService calculationService;

    @Override
    public Set<CalculationParameterMeasurement> save(CalculationParameterMeasurementBuilder builder) {
        if (builder.getDefect() != null) {
            return new HashSet<>(repository.saveAll(setNumbers(builder)
                    .stream()
                    .map(p -> mapper.mapWithDefectMeasurement(p, builder.getDefect()))
                    .toList()));
        }
        if (builder.getRepair() != null) {
            return new HashSet<>(repository.saveAll(setNumbers(builder)
                    .stream()
                    .map(p ->  mapper.mapWithCompletedRepairElement(p, builder.getRepair()))
                    .toList()));
        }
        throw new BadRequestException(
                String.format("DefectMeasurement=%s and CompletedRepairElement=%s should not be null"
                                                                           , builder.getDefect(), builder.getRepair()));
    }

    private Set<CalculationParameterMeasurement> setNumbers(CalculationParameterMeasurementBuilder builder) {
        log.info("START Calculation");
        Set<CalculationParameterMeasurement> calculations = calculationService.calculation(builder);
        log.info("END Calculation");
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
        log.info("4. Before set numbers calculations = {}", calculations);
        return calculations;
    }
}