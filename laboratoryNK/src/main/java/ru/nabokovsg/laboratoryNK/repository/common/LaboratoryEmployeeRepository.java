package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;

public interface LaboratoryEmployeeRepository extends JpaRepository<LaboratoryEmployee, Long> {
}