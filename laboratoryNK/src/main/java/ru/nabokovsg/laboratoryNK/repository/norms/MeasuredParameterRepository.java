package ru.nabokovsg.laboratoryNK.repository.norms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

import java.util.Set;

public interface MeasuredParameterRepository extends JpaRepository<MeasuredParameter, Long> {

    Set<MeasuredParameter> findAllByDefectId(Long defectId);
}