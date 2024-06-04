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
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QCompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QVMSurvey;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.CompletedRepairElementRepository;
import ru.nabokovsg.laboratoryNK.service.norms.ElementRepairService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompletedRepairElementServiceImpl implements CompletedRepairElementService {

    private final CompletedRepairElementRepository repository;
    private final CompletedRepairElementMapper mapper;
    private final ElementRepairService repairService;
    private final ParameterMeasurementService parameterMeasurementService;
    private final EntityManager em;
    private final VMSurveyService vmSurveyService;

    @Override
    public ResponseCompletedRepairElementDto save(CompletedRepairElementDto repairDto) {
        CompletedRepairElement repair = getByPredicate(repairDto);
        ElementRepair elementRepair = repairService.getById(repairDto.getRepairId());
        if (repair == null) {
            repair = repository.save(mapper.mapToCompletedRepairElement(repairDto, elementRepair
                                        , vmSurveyService.save(repairDto.getSurveyJournalId(), repairDto.getEquipmentId()
                                                             , repairDto.getElementId(), repairDto.getPartElementId())));
        }
        repair.getParameterMeasurements().addAll(parameterMeasurementService.saveCompletedRepairElement(elementRepair, repair, repairDto.getParameterMeasurements()));
        return mapper.mapToResponseCompletedRepairElementDto(repair);
    }

    @Override
    public ResponseCompletedRepairElementDto update(CompletedRepairElementDto repairDto) {
        if (repository.existsById(repairDto.getId())) {
            ElementRepair elementRepair = repairService.getById(repairDto.getRepairId());
            CompletedRepairElement repair = repository.save(mapper.mapToCompletedRepairElement(repairDto, elementRepair
                    , vmSurveyService.save(repairDto.getSurveyJournalId(), repairDto.getEquipmentId()
                            , repairDto.getElementId(), repairDto.getPartElementId())));
            repair.getParameterMeasurements().addAll(parameterMeasurementService.saveCompletedRepairElement(elementRepair, repair, repairDto.getParameterMeasurements()));
            return mapper.mapToResponseCompletedRepairElementDto(repair);
        }
        throw new NotFoundException(
                String.format("Completed repair element with id=%s not found for update", repairDto.getId()));
    }

    @Override
    public List<ResponseCompletedRepairElementDto> getAll(Long id) {
        QCompletedRepairElement repair = QCompletedRepairElement.completedRepairElement;
        QVMSurvey vm = QVMSurvey.vMSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(id));
        return new JPAQueryFactory(em).from(repair)
                                      .select(repair)
                                      .where(booleanBuilder)
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToResponseCompletedRepairElementDto)
                                      .toList();
    }

    @Override
    public Set<CompletedRepairElement> getAllByIds(Long surveyJournalId, Long equipmentId) {
        QCompletedRepairElement repair = QCompletedRepairElement.completedRepairElement;
        QVMSurvey vm = QVMSurvey.vMSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(surveyJournalId));
        booleanBuilder.and(vm.equipmentId.eq(equipmentId));
        Set<CompletedRepairElement> repairElements = new HashSet<>(new JPAQueryFactory(em).from(repair)
                                                                                          .select(repair)
                                                                                          .where(booleanBuilder)
                                                                                          .fetch());
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
        QVMSurvey vm = QVMSurvey.vMSurvey;
        QCompletedRepairElement repair = QCompletedRepairElement.completedRepairElement;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(repairDto.getSurveyJournalId()));
        booleanBuilder.and(vm.equipmentId.eq(repairDto.getEquipmentId()));
        booleanBuilder.and(vm.elementId.eq(repairDto.getElementId()));
        booleanBuilder.and(repair.repairId.eq(repairDto.getRepairId()));
        if (repairDto.getPartElementId() != null) {
            booleanBuilder.and(vm.partElementId.eq(repairDto.getPartElementId()));
        }
        return new JPAQueryFactory(em).from(repair)
                .select(repair)
                .innerJoin(vm).on(repair.vmSurvey.id.eq(vm.id))
                .where(booleanBuilder)
                .fetchOne();
    }
}