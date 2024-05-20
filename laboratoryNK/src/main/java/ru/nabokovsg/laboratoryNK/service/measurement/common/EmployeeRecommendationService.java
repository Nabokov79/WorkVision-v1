package ru.nabokovsg.laboratoryNK.service.measurement.common;

import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.EmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.ResponseEmployeeRecommendationDto;

import java.util.List;

public interface EmployeeRecommendationService {

    ResponseEmployeeRecommendationDto save(EmployeeRecommendationDto recommendationDto);

    ResponseEmployeeRecommendationDto update(EmployeeRecommendationDto recommendationDto);

    List<ResponseEmployeeRecommendationDto> getAll(Long id);

   void delete(Long id);
}