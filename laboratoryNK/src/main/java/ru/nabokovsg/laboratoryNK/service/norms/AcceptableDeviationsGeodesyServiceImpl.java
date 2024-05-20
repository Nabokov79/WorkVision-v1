package ru.nabokovsg.laboratoryNK.service.norms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.AcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.dto.norms.geodesy.ResponseAcceptableDeviationsGeodesyDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.norms.AcceptableDeviationsGeodesyMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;
import ru.nabokovsg.laboratoryNK.repository.norms.AcceptableDeviationsGeodesyRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AcceptableDeviationsGeodesyServiceImpl implements AcceptableDeviationsGeodesyService {

    private final AcceptableDeviationsGeodesyRepository repository;
    private final AcceptableDeviationsGeodesyMapper mapper;

    @Override
    public ResponseAcceptableDeviationsGeodesyDto save(AcceptableDeviationsGeodesyDto geodesyDto) {
        return mapper.mapToResponseAcceptableDeviationsGeodesyDto(
                Objects.requireNonNullElseGet(
                        repository.findByEquipmentTypeIdAndFullAndOld(geodesyDto.getEquipmentTypeId()
                                , geodesyDto.getFull()
                                , geodesyDto.getOld())
                        , () -> repository.save(mapper.mapToPermissibleDeviationsGeodesy(geodesyDto))));
    }

    @Override
    public ResponseAcceptableDeviationsGeodesyDto update(AcceptableDeviationsGeodesyDto geodesyDto) {
        if (repository.existsById(geodesyDto.getId())) {
            return mapper.mapToResponseAcceptableDeviationsGeodesyDto(
                    repository.save(mapper.mapToPermissibleDeviationsGeodesy(geodesyDto))
            );
        }
        throw new NotFoundException(
                String.format("Acceptable deviations geodesy with id=%s not found for update", geodesyDto.getId())
        );
    }

    @Override
    public List<ResponseAcceptableDeviationsGeodesyDto> getAll(Long id) {
        return repository.findAllByEquipmentTypeId(id)
                         .stream()
                         .map(mapper::mapToResponseAcceptableDeviationsGeodesyDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Acceptable deviations geodesy with id=%s not found for delete", id));
    }

    @Override
    public AcceptableDeviationsGeodesy getByDataOfEquipmentForCalculations(EquipmentDiagnosed equipment) {
        return repository.findByEquipmentTypeIdAndFullAndOldAndVolume(equipment.getEquipmentType().getId()
                                                                    , equipment.getFull()
                                                                    , equipment.getOld()
                                                                    , equipment.getVolume());
    }
}