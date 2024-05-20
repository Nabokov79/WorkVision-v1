package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ResponseElementDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.element.ShortResponseElementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.equipmentDiagnosed.EquipmentElementMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.QEquipmentElement;
import ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed.EquipmentElementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentElementServiceImpl implements EquipmentElementService {

    private final EquipmentElementRepository repository;
    private final EquipmentElementMapper mapper;
    private final EntityManager em;
    private final EquipmentDiagnosedService equipmentService;
    private final StandardSizeService standardSizeService;

    @Override
    public ShortResponseElementDto save(ElementDto elementDto) {
        EquipmentElement element = getByPredicate(elementDto);
        if (element == null) {
            element = mapper.mapToElement(elementDto, equipmentService.getById(elementDto.getEquipmentId()));
            if (elementDto.getStandardSize() != null) {
                element = mapper.mapElementWithStandardSize(element
                                                         , standardSizeService.save(elementDto.getStandardSize()));
            }
            element = repository.save(element);
        }
        return mapper.mapToShortElementDto(element);
    }

    @Override
    public ShortResponseElementDto update(ElementDto elementDto) {
        if (repository.existsById(elementDto.getId())) {
            return mapper.mapToShortElementDto(
                    repository.save(mapper.mapToElement(elementDto
                                                    , equipmentService.getById(elementDto.getEquipmentId())))
            );
        }
        throw new NotFoundException(String.format("Element with id=%s not found for update", elementDto.getId()));
    }

    @Override
    public ResponseElementDto get(Long id) {
        return mapper.mapToElementDto(getById(id));
    }

    @Override
    public List<ResponseElementDto> getAll(Long id) {
        return repository.findAllByEquipmentDiagnosedId(id)
                         .stream()
                         .map(mapper::mapToElementDto)
                         .toList();
    }

    @Override
    public void addPartElement(Long id, PartElement partElement) {
        EquipmentElement element = getById(id);
        element.getPartsElement().add(partElement);
        repository.save(element);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Element with id=%s not found for delete", id));
    }

    @Override
    public EquipmentElement getById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException(String.format("Element with id=%s not found", id)));
    }

    private EquipmentElement getByPredicate(ElementDto elementDto) {
        QEquipmentElement element = QEquipmentElement.equipmentElement;
        QEquipmentDiagnosed equipment = QEquipmentDiagnosed.equipmentDiagnosed;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(element.elementName.eq(elementDto.getElementName()));
        booleanBuilder.and(equipment.id.eq(elementDto.getEquipmentId()));
        return new JPAQueryFactory(em).from(element)
                .select(element)
                .where(booleanBuilder)
                .fetchOne();
    }
}