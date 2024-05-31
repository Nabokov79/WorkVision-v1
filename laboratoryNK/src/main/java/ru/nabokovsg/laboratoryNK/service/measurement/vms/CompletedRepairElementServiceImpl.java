package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.CompletedRepairElementMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QCompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.CompletedRepairElementRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;
import ru.nabokovsg.laboratoryNK.service.norms.ElementRepairService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompletedRepairElementServiceImpl implements CompletedRepairElementService {

    private final CompletedRepairElementRepository repository;
    private final CompletedRepairElementMapper mapper;
    private final ElementRepairService repairService;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;
    private final EquipmentElementService equipmentElementService;

    @Override
    public ResponseCompletedRepairElementDto save(CompletedRepairElementDto repairDto) {
        CompletedRepairElement repair = getByPredicate(repairDto);
        ElementRepair elementRepair = repairService.getById(repairDto.getRepairId());
        if (repair == null) {
            EquipmentElement element = equipmentElementService.getById(repairDto.getElementId());
            Map<Long, PartElement> partsElement = element.getPartsElement()
                    .stream().collect(Collectors.toMap(PartElement::getId, p -> p));
            repair = mapper.mapWithEquipmentElement(repairDto, elementRepair, element);
            if(repairDto.getPartElementId() != null) {
                repair = mapper.mapWithPartElement(repair, partsElement.get(repairDto.getPartElementId()));
            }
            repair = repository.save(repair);
        }
        repair.getParameterMeasurements().addAll(parameterMeasurementService.saveForCompletedRepair( repair
                                                                       , elementRepair.getTypeCalculation()
                                                                       , elementRepair.getMeasuredParameters()
                                                                       , repair.getParameterMeasurements()
                                                                       , repairDto.getParameterMeasurements()));
        return mapper.mapToResponseCompletedRepairElementDto(repair);
    }

    @Override
    public ResponseCompletedRepairElementDto update(CompletedRepairElementDto repairDto) {
        if (repository.existsById(repairDto.getId())) {
            ElementRepair elementRepair = repairService.getById(repairDto.getRepairId());
            EquipmentElement element = equipmentElementService.getById(repairDto.getElementId());
            Map<Long, PartElement> partsElement = element.getPartsElement()
                    .stream().collect(Collectors.toMap(PartElement::getId, p -> p));
            CompletedRepairElement repair = mapper.mapWithEquipmentElement(repairDto, elementRepair, element);
            if(repairDto.getPartElementId() != null) {
                repair = mapper.mapWithPartElement(repair, partsElement.get(repairDto.getPartElementId()));
            }
            repair = repository.save(repair);
            repair.setParameterMeasurements(parameterMeasurementService.update(elementRepair.getTypeCalculation()
                    , elementRepair.getMeasuredParameters()
                    , repair.getParameterMeasurements()
                    , repairDto.getParameterMeasurements()));
            return mapper.mapToResponseCompletedRepairElementDto(repair);
        }
        throw new NotFoundException(
                String.format("Completed repair element with id=%s not found for update", repairDto.getId()));
    }

    @Override
    public List<ResponseCompletedRepairElementDto> getAll(Long id) {
        return repository.findAllBySurveyJournalId(id)
                         .stream()
                         .map(mapper::mapToResponseCompletedRepairElementDto)
                         .toList();
    }

    @Override
    public Set<CompletedRepairElement> getAllByIds(Long surveyJournalId, Long equipmentId) {
        Set<CompletedRepairElement> repairElements = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (repairElements.isEmpty()) {
            throw new NotFoundException(
                    String.format("CompletedRepairElement with surveyJournalId=%s and surveyJournalId=%s not found"
                            , surveyJournalId, equipmentId));
        }
        return repairElements;
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Completed repair element with id=%s not found for delete", id));
    }

    private CompletedRepairElement getByPredicate(CompletedRepairElementDto repairDto) {
        QCompletedRepairElement repair = QCompletedRepairElement.completedRepairElement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(repair.surveyJournalId.eq(repairDto.getSurveyJournalId()));
        booleanBuilder.and(repair.equipmentId.eq(repairDto.getEquipmentId()));
        booleanBuilder.and(repair.elementId.eq(repairDto.getElementId()));
        if(repairDto.getRepairId() != null) {
            booleanBuilder.and(repair.repairId.eq(repairDto.getRepairId()));
        }
        if (repairDto.getPartElementId() != null) {
            booleanBuilder.and(repair.partElementId.eq(repairDto.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(repair)
                .select(repair)
                .where(booleanBuilder)
                .fetchOne();
    }
}