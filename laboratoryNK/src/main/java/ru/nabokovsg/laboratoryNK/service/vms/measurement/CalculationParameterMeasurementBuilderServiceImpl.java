package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.vms.measurement.CalculationParameterMeasurementBuilderMapper;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameterType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.UnitMeasurementType;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstParameterMeasurementService;
import ru.nabokovsg.laboratoryNK.service.vms.common.ConstUnitMeasurementService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CalculationParameterMeasurementBuilderServiceImpl implements CalculationParameterMeasurementBuilderService {

    private final CalculationParameterMeasurementBuilderMapper mapper;
    private final ConstParameterMeasurementService parameterMeasurementService;
    private final ConstUnitMeasurementService unitMeasurementService;

    @Override
    public Set<CalculationParameterMeasurement> create(CalculationType type
                                                     , Set<MeasuredParameter> measuredParameters
                                                     , Double firstValue
                                                     , Double secondValue) {
        Set<CalculationParameterMeasurement> parameterMeasurements = mapping(type, measuredParameters);
        switch (type) {
            case QUANTITY -> {
                if (searchParameter(measuredParameters)) {
                    parameterMeasurements.add(createQuantityParameter(firstValue));
                }
            }
            case SQUARE -> {
                parameterMeasurements.add(createSquareParameter(firstValue));
                parameterMeasurements.add(createQuantityParameter(secondValue));
            }
        }
        return parameterMeasurements;
    }

    private CalculationParameterMeasurement createSquareParameter(Double square) {
        return mapper.mapToCalculationParameter(
                parameterMeasurementService.get(String.valueOf(MeasuredParameterType.SQUARE))
                                              , square
                                              , null
                                              , unitMeasurementService.get(String.valueOf(UnitMeasurementType.M_2)));
    }

    private CalculationParameterMeasurement createQuantityParameter(Double quantity) {
        return mapper.mapToCalculationParameter(
                parameterMeasurementService.get(String.valueOf(MeasuredParameterType.QUANTITY))
                                              , quantity
                                              , null
                                              , unitMeasurementService.get(String.valueOf(UnitMeasurementType.PIECES)));
    }

    private Set<CalculationParameterMeasurement> mapping(CalculationType type, Set<MeasuredParameter> measuredParameters) {
        Set<CalculationParameterMeasurement> parameterMeasurements = new HashSet<>();
        measuredParameters
                .forEach(m -> {
                    if (valid(type, m.getParameterName())) {
                        parameterMeasurements.add(
                                mapper.mapToCalculationParameter(m.getParameterName()
                                                               , null
                                                               , null
                                                               , m.getUnitMeasurement()));
                    }
                });
        return parameterMeasurements;
    }

    private boolean searchParameter(Set<MeasuredParameter> measuredParameters) {
        String parameterName = parameterMeasurementService.get("QUANTITY");
        for (MeasuredParameter parameter : measuredParameters) {
            if (parameter.getParameterName().equals(parameterName)) {
                return false;
            }
        }
        return true;
    }

    private boolean valid(CalculationType type, String parameterName) {
        boolean flag = true;
        if (type.equals(CalculationType.SQUARE)) {
            String[] names = new String[]{"LENGTH", "WIDTH", "DIAMETER"};
            for (String parameter : Arrays.stream(names).map(parameterMeasurementService::get).toList()) {
                if (parameterName.equals(parameter)) {
                    return false;
                }
            }
        }
        return flag;
    }
}
