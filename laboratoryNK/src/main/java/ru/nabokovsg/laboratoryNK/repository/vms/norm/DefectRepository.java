package ru.nabokovsg.laboratoryNK.repository.vms.norm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;

public interface DefectRepository extends JpaRepository<Defect, Long> {

    Defect findByDefectName(String defectName);
}