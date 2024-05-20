package ru.nabokovsg.laboratoryNK.service.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.measuringTool.MeasuringToolDto;
import ru.nabokovsg.laboratoryNK.dto.measuringTool.ResponseMeasuringToolDto;
import ru.nabokovsg.laboratoryNK.dto.measuringTool.SearchParameters;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.common.MeasuringToolMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.common.QMeasuringTool;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;
import ru.nabokovsg.laboratoryNK.repository.common.MeasuringToolRepository;
import ru.nabokovsg.laboratoryNK.service.documentTemplate.MeasuringToolTemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MeasuringToolServiceImpl implements MeasuringToolService {

    private final MeasuringToolRepository repository;
    private final MeasuringToolMapper mapper;
    private final EntityManager em;
    private final MeasuringToolTemplateService templateService;

    @Override
    public ResponseMeasuringToolDto save(MeasuringToolDto measuringToolDto) {
        MeasuringTool measuringTool = getByPredicate(measuringToolDto);
        if (measuringTool == null) {
            measuringTool = repository.save(mapper.mapToMeasuringTool(measuringToolDto));
            templateService.save(measuringTool);
        }
        return mapper.mapToResponseMeasuringToolDto(measuringTool);
    }

    @Override
    public ResponseMeasuringToolDto update(MeasuringToolDto measuringToolDto) {
        MeasuringTool measuringTool = repository.findById(measuringToolDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(
                               String.format("MeasuringTool with id=%s not found for update", measuringToolDto.getId()))
                );
        if (measuringTool != null) {
            templateService.update(measuringTool);
            return mapper.mapToResponseMeasuringToolDto(repository.save(mapper.mapToMeasuringTool(measuringToolDto)));
        }
        throw new NotFoundException(
                String.format("MeasuringTool with id=%s not found for update", measuringToolDto.getId())
        );
    }

    @Override
    public List<ResponseMeasuringToolDto> getAll(SearchParameters parameters) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        return new JPAQueryFactory(em).from(measuringTool)
                                      .select(measuringTool)
                                      .where(getPredicate(parameters))
                                      .fetch()
                                      .stream()
                                      .map(mapper::mapToResponseMeasuringToolDto)
                                      .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("measuring tool with id = %s not found for delete", id));
    }

    @Override
    public List<MeasuringTool> getByLaboratoryEmployeeDataAndTemplate(Set<LaboratoryEmployee> employees
                                                              , List<MeasuringToolTemplate> measuringToolsTemplates) {
        List<Long> employeesId = employees.stream().map(LaboratoryEmployee::getId).toList();
        List<String> tolls = new ArrayList<>();
        List<String> models = new ArrayList<>();
        measuringToolsTemplates.forEach(t ->{
            tolls.add(t.getToll());
            models.add(t.getModel());
        });
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(measuringTool.laboratoryEmployeeId.in(employeesId));
        booleanBuilder.and(measuringTool.toll.in(tolls));
        booleanBuilder.and(measuringTool.model.in(models));
        return new JPAQueryFactory(em).from(measuringTool)
                .select(measuringTool)
                .where(booleanBuilder)
                .fetch();
    }

    private MeasuringTool getByPredicate(MeasuringToolDto measuringToolDto) {
        QMeasuringTool measuringTool = QMeasuringTool.measuringTool;
        return new JPAQueryFactory(em).from(measuringTool)
                .select(measuringTool)
                .where(getPredicate(mapper.mapToRequestParameters(measuringToolDto)))
                .fetchOne();
    }

    private BooleanBuilder getPredicate(SearchParameters measuringToolDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (measuringToolDto.getIds() != null && !measuringToolDto.getToll().isEmpty()) {
            booleanBuilder.and(QMeasuringTool.measuringTool.toll.in(measuringToolDto.getToll()));
        }
        if (measuringToolDto.getToll() != null && !measuringToolDto.getToll().isEmpty()) {
            booleanBuilder.and(QMeasuringTool.measuringTool.toll.in(measuringToolDto.getToll()));
        }
        if (measuringToolDto.getModel() != null && !measuringToolDto.getModel().isEmpty()) {
            booleanBuilder.and(QMeasuringTool.measuringTool.model.in(measuringToolDto.getModel()));
        }
        if (measuringToolDto.getWorkNumber() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.workNumber.eq(measuringToolDto.getWorkNumber()));
        }
        if (measuringToolDto.getManufacturing() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturing.eq(measuringToolDto.getManufacturing()));
        }
        if (measuringToolDto.getExploitation() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.exploitation.eq(measuringToolDto.getExploitation()));
        }
        if (measuringToolDto.getManufacturer() != null) {
            booleanBuilder.and(QMeasuringTool.measuringTool.manufacturer.eq(measuringToolDto.getManufacturer()));
        }
       return booleanBuilder;
    }
}