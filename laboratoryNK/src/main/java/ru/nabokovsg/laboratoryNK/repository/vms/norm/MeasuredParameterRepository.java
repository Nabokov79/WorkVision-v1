package ru.nabokovsg.laboratoryNK.repository.vms.norm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;

import java.util.Set;

public interface MeasuredParameterRepository extends JpaRepository<MeasuredParameter, Long> {

    Set<MeasuredParameter> findAllByDefectId(Long defectId);
}