package ru.nabokovsg.laboratoryNK.repository.norms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;

public interface DefectRepository extends JpaRepository<Defect, Long> {

    Defect findByDefectName(String defectName);
}