package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.laboratoryEmployee.employees.ResponseLaboratoryEmployeeDto;
import ru.nabokovsg.laboratoryNK.dto.laboratoryEmployee.employees.ShortResponseLaboratoryEmployeeDto;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;

import java.util.List;

public interface LaboratoryEmployeeService {

    List<ShortResponseLaboratoryEmployeeDto> copy(Long id, String divisionType);

    ResponseLaboratoryEmployeeDto get(Long id);

    List<ShortResponseLaboratoryEmployeeDto> getAll();

    void delete(Long id);

    LaboratoryEmployee getById(Long id);

    List<LaboratoryEmployee> getAllById(List<Long> ids);
}