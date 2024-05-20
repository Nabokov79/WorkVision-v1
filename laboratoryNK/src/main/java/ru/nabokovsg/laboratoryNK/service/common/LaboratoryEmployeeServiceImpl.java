package ru.nabokovsg.laboratoryNK.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.client.LaboratoryClient;
import ru.nabokovsg.laboratoryNK.dto.laboratoryEmployee.employees.ResponseLaboratoryEmployeeDto;
import ru.nabokovsg.laboratoryNK.dto.laboratoryEmployee.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.common.LaboratoryEmployeeMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.repository.common.LaboratoryEmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LaboratoryEmployeeServiceImpl implements LaboratoryEmployeeService {

    private final LaboratoryEmployeeRepository repository;
    private final LaboratoryEmployeeMapper mapper;
    private final LaboratoryClient client;
    private final StringBuilderService builderService;

    @Override
    public List<ShortResponseLaboratoryEmployeeDto> copy(Long id, String divisionType) {
        List<LaboratoryEmployee> laboratoryEmployees = client.getAllEmployees(id, divisionType)
                                                             .stream()
                                                             .map(e -> mapper.mapToLaboratoryEmployee(e.getId()
                                                                     , e.getPost()
                                                                     , builderService.buildInitials(e)))
                                                             .toList();
        try {
            laboratoryEmployees = repository.saveAll(laboratoryEmployees);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return laboratoryEmployees.stream()
                                  .map(mapper::mapToShortResponseLaboratoryEmployeeDto)
                                  .toList();
    }

    @Override
    public ResponseLaboratoryEmployeeDto get(Long id) {
        return mapper.mapToResponseLaboratoryEmployeeDto(getById(id));
    }

    @Override
    public List<ShortResponseLaboratoryEmployeeDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToShortResponseLaboratoryEmployeeDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("LaboratoryEmployee with id=%s not found for delete", id));
    }

    @Override
    public LaboratoryEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("LaboratoryEmployee with id=%s not found", id)));
    }

    @Override
    public List<LaboratoryEmployee> getAllById(List<Long> ids) {
        List<LaboratoryEmployee> employees = repository.findAllById(ids);
        if (ids.size() != employees.size()) {
            throw new NotFoundException(String.format("Laboratory Employee with ids=%s not found", ids));
        }
        return employees;
    }
}