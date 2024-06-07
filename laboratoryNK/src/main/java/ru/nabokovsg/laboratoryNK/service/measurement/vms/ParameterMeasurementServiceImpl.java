package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.Builder;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.CalculationType;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.ParameterMeasurementServiceRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParameterMeasurementServiceImpl extends ConstParameterMeasurement implements ParameterMeasurementService {

    private final ParameterMeasurementServiceRepository repository;
    private final ParameterMeasurementMapper mapper;
    private final CalculationParameterMeasurementService calculationService;

    @Override
    public Set<CalculationParameterMeasurement> save(Builder builder) {
        Set<CalculationParameterMeasurement> calculations = new HashSet<>();
        if (builder.getDefectMeasurement().getParameterMeasurements() == null) {
            calculations.addAll(createCalculationParameterMeasurement(builder.getDefect().getTypeCalculation()
                                                                    , builder.getDefect().getMeasuredParameters()));
        }
        if (builder.getCompletedRepair().getParameterMeasurements() == null) {
            calculations.addAll(createCalculationParameterMeasurement(builder.getElementRepair().getTypeCalculation()
                    , builder.getElementRepair().getMeasuredParameters()));
        }
        if (builder.getDefect() != null) {
            calculations = calculationService.calculation(builder.getDefect().getTypeCalculation()
                                                        , builder.getDefect().getMeasuredParameters()
                                                        , builder.getDefectMeasurement().getParameterMeasurements()
                                                        , builder.getParameterMeasurementsDto());
            calculations = calculations.stream()
                                       .map(p -> mapper.mapWithDefectMeasurement(p, builder.getDefectMeasurement()))
                                       .collect(Collectors.toSet());
        }
        if (builder.getElementRepair() != null) {
            calculations = setSequentialNumberParameters(calculationService.calculation(
                                           builder.getElementRepair().getTypeCalculation()
                                         , builder.getElementRepair().getMeasuredParameters()
                                         , builder.getCompletedRepair().getParameterMeasurements()
                                         , builder.getParameterMeasurementsDto()));
            calculations = calculations.stream()
                                       .map(p -> mapper.mapWithCompletedRepairElement(p, builder.getCompletedRepair()))
                                       .collect(Collectors.toSet());
        }
        return new HashSet<>(repository.saveAll(calculations));
    }

    private Set<CalculationParameterMeasurement> setSequentialNumberParameters(Set<CalculationParameterMeasurement> parameterMeasurements) {
        int sequentialNumber = 1;
        int number = 1;
        int size = parameterMeasurements.size();
        for (CalculationParameterMeasurement parameter : parameterMeasurements) {
            if (parameter.getNumber() != null && parameter.getSequentialNumber() != null) {
                if (number < parameter.getNumber()) {
                    number = parameter.getNumber();
                }
                if (sequentialNumber < parameter.getSequentialNumber()) {
                    sequentialNumber = parameter.getSequentialNumber();
                }
                size = size - 1;
            }
            if (parameter.getNumber() == null) {
                if (parameter.getParameterName().equals(getSquare())) {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(sequentialNumber);
                } else if (parameter.getParameterName().equals(getQuantity())) {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(size);
                } else {
                    parameter.setNumber(number);
                    parameter.setSequentialNumber(sequentialNumber + 1);
                }
            }
        }
        return parameterMeasurements;
    }

    private Set<CalculationParameterMeasurement> createCalculationParameterMeasurement(CalculationType typeCalculation, Set<MeasuredParameter> measuredParameters) {
        Set<CalculationParameterMeasurement> parameterMeasurements = new HashSet<>();
        switch (typeCalculation) {
            case SQUARE ->  {
                parameterMeasurements.add(mapper.mapToShortCalculationParameter(getSquare(), null, getM2()));
                parameterMeasurements.add(mapper.mapToShortCalculationParameter(getQuantity(), 0.0, getPieces()));
                measuredParameters.forEach(m -> {
                    if (!m.getParameterName().equals(getLength()) && !m.getParameterName().equals(getWidth()) && !m.getParameterName().equals(getDiameter())) {
                        log.info("Create ParameterName: {}", m.getParameterName());
                        parameterMeasurements.add(mapper.mapToShortCalculationParameter(m.getParameterName(), null, m.getUnitMeasurement()));
                    }
                });
            }
            case QUANTITY -> {
                measuredParameters.forEach(m -> parameterMeasurements.add(mapper.mapToShortCalculationParameter(m.getParameterName(), null, m.getUnitMeasurement())));
                parameterMeasurements.add(mapper.mapToShortCalculationParameter(getQuantity(), null, getPieces()));
            }
        }
        return parameterMeasurements;
    }
}