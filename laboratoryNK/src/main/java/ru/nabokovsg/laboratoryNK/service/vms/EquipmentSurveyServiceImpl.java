package ru.nabokovsg.laboratoryNK.service.vms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.vms.EquipmentSurveyMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;
import ru.nabokovsg.laboratoryNK.model.vms.QEquipmentSurvey;
import ru.nabokovsg.laboratoryNK.repository.vms.EquipmentSurveyRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;

import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentSurveyServiceImpl implements EquipmentSurveyService {

    private final EquipmentSurveyRepository repository;
    private final EquipmentSurveyMapper mapper;
    private final EntityManager em;
    private final EquipmentElementService equipmentElementService;

    @Override
    public EquipmentSurvey save(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId) {
        EquipmentSurvey vmSurvey = getByPredicate(surveyJournalId, equipmentId, elementId, partElementId);
        if (vmSurvey == null) {
            EquipmentElement element = equipmentElementService.getById(elementId);
            vmSurvey = mapper.mapToVMSurvey(surveyJournalId, equipmentId, element);
            if(partElementId != null) {
                Map<Long, PartElement> partsElement = element.getPartsElement()
                        .stream().collect(Collectors.toMap(PartElement::getId, p -> p));
                vmSurvey = mapper.mapWithPartElement(vmSurvey, partsElement.get(partElementId));
            }
            return repository.save(vmSurvey);
        }
        return vmSurvey;
    }

    @Override
    public EquipmentSurvey saveWithVisualInspection(VisualInspectionDto inspectionDto) {
        return repository.save(mapper.mapWithVisualInspection(save(inspectionDto.getSurveyJournalId()
                                                                 , inspectionDto.getEquipmentId()
                                                                 , inspectionDto.getElementId()
                                                                 , null)
                            , inspectionDto.getInspection()));
    }

    @Override
    public Set<EquipmentSurvey> getAll(Long surveyJournalId, Long equipmentId) {
        Set<EquipmentSurvey> vm = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (vm.isEmpty()) {
            throw new NotFoundException(
                    String.format("VMSurvey by surveyJournalId=%s and equipmentId=%s not found", surveyJournalId
                                                                                               , equipmentId));
        }
        return vm;
    }

    private EquipmentSurvey getByPredicate(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId) {
        QEquipmentSurvey vm = QEquipmentSurvey.equipmentSurvey;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(vm.surveyJournalId.eq(surveyJournalId));
        booleanBuilder.and(vm.equipmentId.eq(equipmentId));
        booleanBuilder.and(vm.elementId.eq(elementId));
        if (partElementId != null && partElementId > 0) {
            booleanBuilder.and(vm.partElementId.eq(partElementId));
        }
        return new JPAQueryFactory(em).from(vm)
                .select(vm)
                .where(booleanBuilder)
                .fetchOne();
    }
}
