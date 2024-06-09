package ru.nabokovsg.laboratoryNK.mapper.vms.measurement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;

@Mapper(componentModel = "spring")
public interface CalculationParameterMeasurementBuilderMapper {

    @Mapping(source = "parameterName", target = "parameterName")
    @Mapping(source = "firstValue", target = "firstValue")
    @Mapping(source = "secondValue", target = "secondValue")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(target = "defectMeasurement", ignore = true)
    @Mapping(target = "completedRepairElement", ignore = true)
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapToCalculationParameter(String parameterName
                                                            , Double firstValue
                                                            , Double secondValue
                                                            , String unitMeasurement);
}
