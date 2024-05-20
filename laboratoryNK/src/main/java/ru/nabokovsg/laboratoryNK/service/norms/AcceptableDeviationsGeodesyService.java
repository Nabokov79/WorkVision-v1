package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.AcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;

import java.util.List;

public interface AcceptableDeviationsGeodesyService {

    ResponseAcceptableDeviationsGeodesyDto save(AcceptableDeviationsGeodesyDto geodesyDto);

    ResponseAcceptableDeviationsGeodesyDto update(AcceptableDeviationsGeodesyDto geodesyDto);

    List<ResponseAcceptableDeviationsGeodesyDto> getAll(Long id);

    void delete(Long id);

    AcceptableDeviationsGeodesy getByDataOfEquipmentForCalculations(EquipmentDiagnosed equipment);
}