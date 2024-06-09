package ru.nabokovsg.laboratoryNK.mapper.vms.norm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;

@Mapper(componentModel = "spring")
public interface MeasuredParameterMapper {

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "defect", target = "defect")
    @Mapping(source = "calculationType", target = "typeCalculation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elementRepair", ignore = true)
    MeasuredParameter mapForDefect(String parameterMeasurement, String unitMeasurement
                                 , Defect defect, CalculationType calculationType);

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "elementRepair", target = "elementRepair")
    @Mapping(source = "calculationType", target = "typeCalculation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "defect", ignore = true)
    MeasuredParameter mapForElementRepair(String parameterMeasurement
                                        , String unitMeasurement
                                        , ElementRepair elementRepair
                                        , CalculationType calculationType);

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "id", target = "id")
    MeasuredParameter mapToUpdateMeasuredParameter(Long id, String parameterMeasurement, String unitMeasurement);
}