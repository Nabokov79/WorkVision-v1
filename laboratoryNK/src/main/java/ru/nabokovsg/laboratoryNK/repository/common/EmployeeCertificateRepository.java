package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.EmployeeCertificate;

import java.util.Set;

public interface EmployeeCertificateRepository extends JpaRepository<EmployeeCertificate, Long> {

    EmployeeCertificate findByControlTypeAndEmployeeId(String controlType, Long employeeId);

    Set<EmployeeCertificate> findAllByEmployeeId(Long employeeId);
}