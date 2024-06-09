package ru.nabokovsg.laboratoryNK.service.vms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.vms.VMSurveyMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.PartElement;
import ru.nabokovsg.laboratoryNK.model.vms.QVMSurvey;
import ru.nabokovsg.laboratoryNK.model.vms.VMSurvey;
import ru.nabokovsg.laboratoryNK.repository.vms.VMSurveyRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;

import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VMSurveyServiceImpl implements VMSurveyService {

    private final VMSurveyRepository repository;
    private final VMSurveyMapper mapper;
    private final EntityManager em;
    private final EquipmentElementService equipmentElementService;

    @Override
    public VMSurvey save(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId) {
        VMSurvey vmSurvey = getByPredicate(surveyJournalId, equipmentId, elementId, partElementId);
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
    public VMSurvey saveWithVisualInspection(VisualInspectionDto inspectionDto) {
        return repository.save(mapper.mapWithVisualInspection(save(inspectionDto.getSurveyJournalId()
                                                                 , inspectionDto.getEquipmentId()
                                                                 , inspectionDto.getElementId()
                                                                 , null)
                            , inspectionDto.getInspection()));
    }

    @Override
    public Set<VMSurvey> getAll(Long surveyJournalId, Long equipmentId) {
        Set<VMSurvey> vm = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (vm.isEmpty()) {
            throw new NotFoundException(
                    String.format("VMSurvey by surveyJournalId=%s and equipmentId=%s not found", surveyJournalId
                                                                                               , equipmentId));
        }
        return vm;
    }

    private VMSurvey getByPredicate(Long surveyJournalId, Long equipmentId, Long elementId, Long partElementId) {
        QVMSurvey vm = QVMSurvey.vMSurvey;
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
