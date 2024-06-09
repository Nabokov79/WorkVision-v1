package ru.nabokovsg.laboratoryNK.mapper.vms.measurement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;

@Mapper(componentModel = "spring")
public interface ParameterMeasurementMapper {

    @Mapping(source = "defect", target = "defectMeasurement")
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapWithDefectMeasurement(@MappingTarget CalculationParameterMeasurement parameter
                                                                          , DefectMeasurement defect);

    @Mapping(source = "repair", target = "completedRepairElement")
    @Mapping(target = "id", ignore = true)
    CalculationParameterMeasurement mapWithCompletedRepairElement(
                               @MappingTarget CalculationParameterMeasurement parameter, CompletedRepairElement repair);


}