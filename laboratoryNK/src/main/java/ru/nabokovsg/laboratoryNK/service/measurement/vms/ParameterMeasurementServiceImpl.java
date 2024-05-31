package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
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
        parameterMeasurements = validCalculationParameter(measuredParameters
                                                        , parameterMeasurements
                                                        , parameterMeasurementsDto);
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
        parameterMeasurements = validCalculationParameter(measuredParameters
                                                        , parameterMeasurements
                                                        , parameterMeasurementsDto);
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
        return new HashSet<>(repository.saveAll(calculationParameters(typeCalculation
                                                                    , parameterMeasurements
                                                                    , parameterMeasurementsDto
                                                                    , measuredParameters)));
    }

    private Set<CalculationParameterMeasurement> calculationParameters(TypeCalculation typeCalculation
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto
            , Set<MeasuredParameter> measuredParameters) {
        final Map<Long, CalculationParameterMeasurement> parameterMeasurementsDb = parameterMeasurements.stream()
                                        .collect(Collectors.toMap(CalculationParameterMeasurement::getId, c -> c));
        switch (typeCalculation) {
            case QUANTITY, SQUARE -> { return calculationService.calculationDefectOrRepair(typeCalculation
                    , parameterMeasurementsDb
                    , parameterMeasurementsDto);}
            case MAX, MIN, MAX_MIN -> {
                Map<Long, MeasuredParameter> measuredParametersDb = measuredParameters.stream()
                        .collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
                return parameterMeasurementsDto.stream()
                        .map(p -> calculationService.calculationParameterMeasurement(measuredParametersDb.get(p.getParameterId())
                                , parameterMeasurementsDb.get(p.getParameterId())
                                , p))
                        .collect(Collectors.toSet());
            }
            default -> {return parameterMeasurements;}
        }
    }

    private Set<CalculationParameterMeasurement> validCalculationParameter(Set<MeasuredParameter> measuredParameters
                                                            , Set<CalculationParameterMeasurement> parameterMeasurements
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto) {
        if(parameterMeasurements.isEmpty()) {
            Map<Long, MeasuredParameter> measuredParametersDb = measuredParameters.stream()
                                                           .collect(Collectors.toMap(MeasuredParameter::getId, m -> m));
            return parameterMeasurementsDto.stream()
                                           .map(p -> mapper.mapToParameterMeasurement(p
                                                                        , measuredParametersDb.get(p.getParameterId())))
                                           .collect(Collectors.toSet());
        }
        return parameterMeasurements;
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