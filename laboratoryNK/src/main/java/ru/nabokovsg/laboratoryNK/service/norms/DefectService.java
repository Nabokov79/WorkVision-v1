package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.norms.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;

import java.util.List;

public interface DefectService {

    ResponseDefectDto save(DefectDto defectDto);

    ResponseDefectDto update(DefectDto defectDto);

    ResponseDefectDto get(Long id);

    List<ResponseShortDefectDto> getAll();

    void delete(Long id);

    Defect getById(Long id);
}