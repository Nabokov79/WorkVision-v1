package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.vms.VisualInspectionMapper;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.QVisualInspection;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VisualInspection;
import ru.nabokovsg.laboratoryNK.repository.measurement.vms.VisualInspectionRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentElementService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VisualInspectionServiceImpl implements VisualInspectionService {

    private final VisualInspectionRepository repository;
    private final VisualInspectionMapper mapper;
    private final EntityManager em;
    private final EquipmentElementService equipmentElementService;

    @Override
    public ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto) {
        VisualInspection inspection = getByPredicate(inspectionDto);
        if (inspection == null) {
            inspection = repository.save(mapper.mapToVisualInspection(inspectionDto
                                                     , equipmentElementService.getById(inspectionDto.getElementId())));
        }
        return mapper.mapToResponseVisualInspectionDto(inspection);
    }

    @Override
    public ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto) {
        if (repository.existsById(inspectionDto.getId())) {
            return mapper.mapToResponseVisualInspectionDto(
                    repository.save(mapper.mapToVisualInspection(inspectionDto
                                                   , equipmentElementService.getById(inspectionDto.getElementId()))));
        }
        throw new NotFoundException(
                String.format("VisualInspection with id=%s not found for update", inspectionDto.getId()));
    }

    @Override
    public List<ResponseVisualInspectionDto> getAll(Long id) {
        return repository.findAllBySurveyJournalId(id)
                         .stream()
                         .map(mapper::mapToResponseVisualInspectionDto)
                         .toList();
    }

    @Override
    public Set<VisualInspection> getAllByIds(Long surveyJournalId, Long equipmentId) {
        Set<VisualInspection> visualInspections = repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (visualInspections.isEmpty()) {
            throw new NotFoundException(
                    String.format("VisualInspection with surveyJournalId=%s and surveyJournalId=%s not found"
                            , surveyJournalId, equipmentId));
        }
        return visualInspections;
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("VisualInspection with id=%s not found for delete", id));
    }

    private VisualInspection getByPredicate(VisualInspectionDto inspectionDto) {
        QVisualInspection inspection = QVisualInspection.visualInspection;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(inspection.surveyJournalId.eq(inspectionDto.getSurveyJournalId()));
        booleanBuilder.and(inspection.equipmentId.eq(inspectionDto.getEquipmentId()));
        booleanBuilder.and(inspection.elementId.eq(inspectionDto.getElementId()));
        booleanBuilder.and(inspection.inspection.eq(inspectionDto.getInspection()));
        return new JPAQueryFactory(em).from(inspection)
                                      .select(inspection)
                                      .where(booleanBuilder)
                                      .fetchOne();
    }
}