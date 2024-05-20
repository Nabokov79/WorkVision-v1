package ru.nabokovsg.laboratoryNK.repository.norms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;

import java.util.Set;

public interface AcceptableDeviationsGeodesyRepository extends JpaRepository<AcceptableDeviationsGeodesy, Long> {

    AcceptableDeviationsGeodesy findByEquipmentTypeIdAndFullAndOld(Long equipmentTypeId, Boolean full, Boolean old);

    Set<AcceptableDeviationsGeodesy> findAllByEquipmentTypeId(Long equipmentTypeId);

    AcceptableDeviationsGeodesy findByEquipmentTypeIdAndFullAndOldAndVolume(Long equipmentTypeId, Boolean full
                                                                          , Boolean old, Integer volume);
}