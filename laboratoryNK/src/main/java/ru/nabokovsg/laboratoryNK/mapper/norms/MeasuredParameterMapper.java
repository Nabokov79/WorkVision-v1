package ru.nabokovsg.laboratoryNK.mapper.norms;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.norms.TypeOfParameterCalculation;

@Mapper(componentModel = "spring")
public interface MeasuredParameterMapper {

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "defect", target = "defect")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elementRepair", ignore = true)
    MeasuredParameter mapForDefect(String parameterMeasurement, String unitMeasurement
                                 , Defect defect, TypeOfParameterCalculation typeCalculation);

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "elementRepair", target = "elementRepair")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "defect", ignore = true)
    MeasuredParameter mapForElementRepair(String parameterMeasurement
                                        , String unitMeasurement
                                        , ElementRepair elementRepair, TypeOfParameterCalculation typeCalculation);

    @Mapping(source = "parameterMeasurement", target = "parameterName")
    @Mapping(source = "unitMeasurement", target = "unitMeasurement")
    @Mapping(source = "id", target = "id")
    MeasuredParameter mapToUpdateMeasuredParameter(Long id, String parameterMeasurement, String unitMeasurement);
}