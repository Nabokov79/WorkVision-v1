package ru.nabokovsg.laboratoryNK.mapper.measurement.common;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.EmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.ResponseEmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.model.measurement.common.EmployeeRecommendation;

@Mapper(componentModel = "spring")
public interface EmployeeRecommendationMapper {

    EmployeeRecommendation mapToEmployeeRecommendation(EmployeeRecommendationDto recommendationDto);

    ResponseEmployeeRecommendationDto mapToResponseEmployeeRecommendationDto(EmployeeRecommendation recommendation);
}