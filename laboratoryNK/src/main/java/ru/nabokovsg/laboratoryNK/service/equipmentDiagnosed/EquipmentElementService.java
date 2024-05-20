package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ResponseElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ShortResponseElementDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;

import java.util.List;

public interface EquipmentElementService {

    ShortResponseElementDto save(ElementDto elementDto);

    ShortResponseElementDto update(ElementDto elementDto);

    ResponseElementDto get(Long id);

    List<ResponseElementDto> getAll(Long id);

    EquipmentElement getById(Long id);

    void addPartElement(Long id, PartElement partElement);

   void delete(Long id);
}