package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.ParameterMeasurementMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.common.ConstParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.ParameterMeasurementServiceRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ParameterMeasurementServiceImpl extends ConstParameterMeasurement implements ParameterMeasurementService {

    private final ParameterMeasurementServiceRepository repository;
    private final ParameterMeasurementMapper mapper;
    private final CalculationParameterMeasurementService calculationService;

    @Override
    public Set<CalculationParameterMeasurement> saveForDefect(DefectMeasurement measurement
                                                            , TypeCalculation typeCalculation
                                                            , Set<MeasuredParameter> measuredParameters
                                                            , Set<CalculationParameterMeasurement> parameterMeasurements
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        return new HashSet<>(repository.saveAll(setNumbers(calculationParameters(typeCalculation
                                                                               , parameterMeasurements
                                                                               , parameterMeasurementsDto
                                                                               , measuredParameters)
                                                                       , parameterMeasurements
                                                                       , measuredParameters.size()))
                                                              .stream()
                                                              .map(p -> mapper.mapWithDefectMeasurement(p, measurement))
                                                              .toList());
    }

    @Override
    public Set<CalculationParameterMeasurement> saveForCompletedRepair(CompletedRepairElement repair
                                                            , TypeCalculation typeCalculation
                                                            , Set<MeasuredParameter> measuredParameters
                                                            , Set<CalculationParameterMeasurement> parameterMeasurements
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        return new HashSet<>(repository.saveAll(setNumbers(calculationParameters(typeCalculation
                                                                               , parameterMeasurements
                                                                               , parameterMeasurementsDto
                                                                               , measuredParameters)
                                                                        , parameterMeasurements
                                                                        , measuredParameters.size()))
                                                              .stream()
                                                              .map(p -> mapper.mapWithCompletedRepairElement(p, repair))
                                                              .toList());
    }

    @Override
    public Set<CalculationParameterMeasurement> update(TypeCalculation typeCalculation
            , Set<MeasuredParameter> measuredParameters
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        return new HashSet<>(repository.saveAll(calculation(typeCalculation
                , parameterMeasurements
                , parameterMeasurementsDto)));
    }

    private Set<CalculationParameterMeasurement> calculationParameters(TypeCalculation typeCalculation
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto
            , Set<MeasuredParameter> measuredParameters) {
        Set<CalculationParameterMeasurement> calculations = calculation(typeCalculation
                , parameterMeasurements
                , parameterMeasurementsDto);
        if (calculations.isEmpty()) {
            Map<Long, MeasuredParameter> measuredParametersDb = measuredParameters.stream()
                                                           .collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
            Map<String, CalculationParameterMeasurement> parameterMeasurementsDb = parameterMeasurements.stream()
                                  .collect(Collectors.toMap(CalculationParameterMeasurement::getParameterName, c -> c));
            calculations = parameterMeasurementsDto.stream()
                                                   .map(p -> {
                MeasuredParameter measuredParameter = measuredParametersDb.get(p.getParameterId());
                return calculationParameterMeasurement(measuredParameter
                                                     , parameterMeasurementsDb.get(measuredParameter.getParameterName())
                                                     , p);
            })
                                                    .collect(Collectors.toSet());
        }
        return calculations;
    }

    private Set<CalculationParameterMeasurement> calculation(TypeCalculation typeCalculation
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        Map<Long, CalculationParameterMeasurement> parameterMeasurementsDb = parameterMeasurements.stream()
                .collect(Collectors.toMap(CalculationParameterMeasurement::getId, r -> r));
        switch (typeCalculation) {
            case SQUARE -> {
                return calculationService.countSquare(
                        parameterMeasurements.stream()
                                .collect(Collectors.toMap(CalculationParameterMeasurement::getId, p -> p))
                        , parameterMeasurementsDto.stream()
                                .collect(Collectors.toMap(p -> parameterMeasurementsDb.get(p.getParameterId())
                                                .getParameterName()
                                        , p -> p)));
            }
            case QUANTITY -> {
                return calculationService.countQuantity(
                        parameterMeasurements.stream()
                                .collect(Collectors.toMap(CalculationParameterMeasurement::getId, p -> p))
                        , parameterMeasurementsDto.stream()
                                .collect(Collectors.toMap(p -> parameterMeasurementsDb.get(p.getParameterId())
                                                .getParameterName()
                                        , p -> p)));
            }
            default -> {
                return new HashSet<>();
            }
        }
    }

    private CalculationParameterMeasurement calculationParameterMeasurement(MeasuredParameter measuredParameter
            , CalculationParameterMeasurement parameterMeasurement
            , ParameterMeasurementDto parameterMeasurementDto) {
        switch (measuredParameter.getTypeCalculation()) {
            case MIN -> {
                return calculationService.countMin(parameterMeasurement, parameterMeasurementDto);
            }
            case MAX -> {
                return calculationService.countMax(parameterMeasurement, parameterMeasurementDto);
            }
            case MAX_MIN -> {
                return calculationService.countMaxAndMin(parameterMeasurement, parameterMeasurementDto);
            }
            case NO_ACTION -> {
                return mapper.mapToParameterMeasurement(parameterMeasurementDto, measuredParameter);
            }
            default -> throw new NotFoundException(String.format("Unknown type=%s calculation parameters"
                    , measuredParameter.getTypeCalculation()));
        }
    }

    private Set<CalculationParameterMeasurement> setNumbers(Set<CalculationParameterMeasurement> calculations
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , int size) {
        int sequentialNumber = 1;
        int number = 1;
        if (!parameterMeasurements.isEmpty()) {
            for (CalculationParameterMeasurement parameterMeasurement : parameterMeasurements) {
                if (parameterMeasurement.getNumber() > number) {
                    number = parameterMeasurement.getNumber() + number;
                }
            }
        }
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
        return Stream.of(calculations, parameterMeasurements)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}