package ru.nabokovsg.laboratoryNK.service.measurement.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.EmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.employeeRecommendation.ResponseEmployeeRecommendationDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.common.EmployeeRecommendationMapper;
import ru.nabokovsg.laboratoryNK.repository.measurement.common.EmployeeRecommendationRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeRecommendationServiceImpl implements EmployeeRecommendationService {

    private final EmployeeRecommendationRepository repository;
    private final EmployeeRecommendationMapper mapper;

    @Override
    public ResponseEmployeeRecommendationDto save(EmployeeRecommendationDto recommendationDto) {
        return mapper.mapToResponseEmployeeRecommendationDto(
                Objects.requireNonNullElseGet(
                        repository.findByEquipmentIdAndRecommendationText(recommendationDto.getEquipmentId()
                                                                , recommendationDto.getRecommendationText())
                        , () -> repository.save(mapper.mapToEmployeeRecommendation(recommendationDto))));
    }

    @Override
    public ResponseEmployeeRecommendationDto update(EmployeeRecommendationDto recommendationDto) {
        if (repository.existsById(recommendationDto.getId())) {
            return mapper.mapToResponseEmployeeRecommendationDto(
                    repository.save(mapper.mapToEmployeeRecommendation(recommendationDto)));
        }
        throw new NotFoundException(
                String.format("Employee recommendation with id=%s not found for update", recommendationDto.getId()));
    }

    @Override
    public List<ResponseEmployeeRecommendationDto> getAll(Long id) {
        return repository.findAllByEquipmentId(id)
                         .stream()
                         .map(mapper::mapToResponseEmployeeRecommendationDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Employee recommendation with id=%s not found for delete", id));
    }
}