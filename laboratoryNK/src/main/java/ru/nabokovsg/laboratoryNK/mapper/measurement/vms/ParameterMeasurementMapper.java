package ru.nabokovsg.laboratoryNK.mapper.measurement.vms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

@Mapper(componentModel = "spring")
public interface ParameterMeasurementMapper {

    @Mapping(source = "measuredParameter.parameterName", target = "parameterName")
    @Mapping(source = "parameterMeasurementDto.value", target = "firstValue")
    @Mapping(source = "measuredParameter.unitMeasurement", target = "unitMeasurement")
    @Mapping(target = "defectMeasurement", ignore = true)
    @Mapping(target = "completedRepairElement", ignore = true)
    @Mapping(target = "secondValue", ignore = true)
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapToParameterMeasurement(ParameterMeasurementDto parameterMeasurementDto
                                                            , MeasuredParameter measuredParameter);

    @Mapping(source = "measuredParameter.parameterName", target = "parameterName")
    @Mapping(target = "firstValue", ignore = true)
    @Mapping(source = "measuredParameter.unitMeasurement", target = "unitMeasurement")
    @Mapping(target = "defectMeasurement", ignore = true)
    @Mapping(target = "completedRepairElement", ignore = true)
    @Mapping(target = "secondValue", ignore = true)
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapToNewParameterMeasurement(MeasuredParameter measuredParameter);

    @Mapping(source = "defect", target = "defectMeasurement")
    CalculationParameterMeasurement mapWithDefectMeasurement(@MappingTarget CalculationParameterMeasurement parameter
                                                                          , DefectMeasurement defect);

    @Mapping(source = "repair", target = "completedRepairElement")
    CalculationParameterMeasurement mapWithCompletedRepairElement(
                               @MappingTarget CalculationParameterMeasurement parameter, CompletedRepairElement repair);

    @Mapping(source = "parameterName", target = "parameterName")
    @Mapping(source = "firstValue", target = "firstValue")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(target = "defectMeasurement", ignore = true)
    @Mapping(target = "completedRepairElement", ignore = true)
    @Mapping(target = "secondValue", ignore = true)
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapToShortCalculationParameter(String parameterName, Double firstValue, String unitMeasurement);
}