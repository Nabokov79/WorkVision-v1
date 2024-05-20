package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.PartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.partElement.ShortResponsePartElementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed.PartElementMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed.PartElementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartElementServiceImpl implements PartElementService {

    private final PartElementRepository repository;
    private final PartElementMapper mapper;
    private final EquipmentElementService elementService;
    private final StandardSizeService standardSizeService;

    @Override
    public ResponsePartElementDto save(PartElementDto partElementDto) {
        PartElement partElement = repository.findByElementIdAndPartName(partElementDto.getElementId()
                , partElementDto.getPartName());
        if (partElement == null) {
            partElement = mapper.mapToPartElement(partElementDto
                    , elementService.getById(partElementDto.getElementId()));
            if (partElementDto.getStandardSize() != null) {
                partElement = mapper.mapPartElementWithStandardSize(partElement
                                            , standardSizeService.save(partElementDto.getStandardSize()));
            }
            partElement = repository.save(partElement);
            elementService.addPartElement(partElementDto.getElementId(), partElement);
        }
        return mapper.mapToFullPartElementDto(partElement);
    }

    @Override
    public ResponsePartElementDto update(PartElementDto partElementDto) {
        if (repository.existsById(partElementDto.getId())) {
            PartElement partElement = mapper.mapToPartElement(partElementDto
                    , elementService.getById(partElementDto.getElementId()));
            if (partElementDto.getStandardSize() != null) {
                partElement = mapper.mapPartElementWithStandardSize(partElement
                                            , standardSizeService.update(partElementDto.getStandardSize()));
            }
            return mapper.mapToFullPartElementDto(repository.save(partElement));
        }
        throw new NotFoundException(
                String.format("PartElement with id=%s not found for update", partElementDto.getId())
        );
    }

    @Override
    public ResponsePartElementDto get(Long id) {
        return mapper.mapToFullPartElementDto(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("PartElement with id=%s not found", id)))
        );
    }

    @Override
    public List<ShortResponsePartElementDto> getAll(Long id) {
        return repository.findAllByElementId(id)
                         .stream()
                         .map(mapper::mapToShortPartElementDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("PartElement with id=%s not found for delete", id));
    }
}