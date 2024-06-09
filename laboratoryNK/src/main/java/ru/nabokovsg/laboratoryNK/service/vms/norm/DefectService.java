package ru.nabokovsg.laboratoryNK.service.vms.norm;

import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;

import java.util.List;

public interface DefectService {

    ResponseDefectDto save(DefectDto defectDto);

    ResponseDefectDto update(DefectDto defectDto);

    ResponseDefectDto get(Long id);

    List<ResponseShortDefectDto> getAll();

    void delete(Long id);

    Defect getById(Long id);
}