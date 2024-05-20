package ru.nabokovsg.laboratoryNK.service.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.client.LaboratoryClient;
import ru.nabokovsg.laboratoryNK.dto.client.BranchDto;
import ru.nabokovsg.laboratoryNK.dto.client.ExploitationRegionDto;
import ru.nabokovsg.laboratoryNK.dto.client.HeatSupplyAreaDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.common.SurveyJournalMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.common.QSurveyJournal;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.repository.common.SurveyJournalRepository;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common.DiagnosticDocumentService;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentDiagnosedService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SurveyJournalServiceImpl implements SurveyJournalService {

    private final SurveyJournalRepository repository;
    private final SurveyJournalMapper mapper;
    private final EntityManager em;
    private final LaboratoryClient client;
    private final LaboratoryEmployeeService employeeService;
    private final StringBuilderService builderService;
    private final DiagnosticDocumentService documentService;
    private final EquipmentDiagnosedService equipmentDiagnosedService;

    @Override
    public ResponseSurveyJournalDto save(SurveyJournalDto journalDto) {
        if (repository.existsByDateAndEquipmentId(journalDto.getDate(), journalDto.getEquipmentId())) {
            throw new BadRequestException(
                    String.format("TasksJournal by date=%s and equipmentId=%s is found", journalDto.getDate()
                                                                                       , journalDto.getEquipmentId())
            );
        }
        EquipmentDiagnosed equipment = equipmentDiagnosedService.getById(journalDto.getEquipmentId());
        equipment.setFull(journalDto.getFull());
        ResponseSurveyJournalDto journal = mapper.mapToResponseTaskJournalDto(
                                                         repository.save(getSurveyJournalData(journalDto, equipment))
        );
        documentService.save(journalDto, journal);
        return journal;
    }

    @Override
    public ResponseSurveyJournalDto update(SurveyJournalDto journalDto) {
        if (repository.existsById(journalDto.getId())) {
            documentService.validateByStatus(journalDto.getId());
            EquipmentDiagnosed equipment = equipmentDiagnosedService.getById(journalDto.getEquipmentId());
            equipment.setFull(journalDto.getFull());
            ResponseSurveyJournalDto journal = mapper.mapToResponseTaskJournalDto(
                    repository.save(getSurveyJournalData(journalDto, equipment))
            );
            documentService.update(journalDto, journal);
            return journal;
        }
        throw new NotFoundException(
                String.format("TasksJournal with id=%s not found for update", journalDto.getId())
        );
    }

    @Override
    public SurveyJournal getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->  new NotFoundException(
                        String.format("SurveyJournal with id=%s not found for delete", id)));
    }

    @Override
    public List<ResponseSurveyJournalDto> getAll(LocalDate startPeriod, LocalDate endPeriod) {
        QSurveyJournal journal = QSurveyJournal.surveyJournal;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (startPeriod != null && endPeriod != null) {
            booleanBuilder.and(journal.date.after(startPeriod));
            booleanBuilder.and(journal.date.before(endPeriod));
        } else {
            booleanBuilder.and(journal.date.eq(LocalDate.now()));
        }
        return new JPAQueryFactory(em).from(journal)
                .select(journal)
                .where(booleanBuilder)
                .fetch()
                .stream()
                .map(mapper::mapToResponseTaskJournalDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("SurveyJournal with id=%s not found for delete", id));
    }

    private SurveyJournal getSurveyJournalData(SurveyJournalDto journalDto, EquipmentDiagnosed equipment) {
        BranchDto branch = client.getBranch(journalDto.getBranchId());
        HeatSupplyAreaDto heatSupplyArea = branch.getHeatSupplyAreas()
                                                 .stream()
                                                 .collect(Collectors.toMap(HeatSupplyAreaDto::getId, h -> h))
                                                 .get(journalDto.getHeatSupplyAreaId());
        ExploitationRegionDto exploitationRegion = heatSupplyArea.getExploitationRegions()
                                                        .stream()
                                                        .collect(Collectors.toMap(ExploitationRegionDto::getId, e -> e))
                                                        .get(journalDto.getExploitationRegionId());
        return setLaboratoryEmployee(mapper.mapToSurveyJournal(journalDto
                                                       , branch.getFullName()
                                                       , heatSupplyArea.getFullName()
                                                       , exploitationRegion.getFullName()
                                                       , builderService.buildBuilding(
                                                         exploitationRegion.getBuildings()
                                                        .stream()
                                                        .collect(Collectors.toMap(b -> b.getAddress().getId(), b -> b))
                                                        .get(journalDto.getAddressId()))
                                                       , equipment.getId()
                                                       , builderService.buildEquipmentDiagnosed(equipment))
                                        , journalDto);
    }

    private SurveyJournal setLaboratoryEmployee(SurveyJournal journal, SurveyJournalDto taskJournalDto) {
        Map<Long, LaboratoryEmployee> employees = employeeService.getAllById(
                                                    Stream.of(taskJournalDto.getLaboratoryEmployeesIds()
                                                                    , List.of(taskJournalDto.getLaboratoryEmployeeId()))
                                                            .flatMap(Collection::stream)
                                                            .toList())
                                            .stream()
                                            .collect(Collectors.toMap(LaboratoryEmployee::getId, l -> l));
        journal.setChief(employees.get(taskJournalDto.getLaboratoryEmployeeId()));
        journal.setEmployees(taskJournalDto.getLaboratoryEmployeesIds()
                                               .stream()
                                               .map(employees::get)
                                               .collect(Collectors.toSet()));
        return journal;
    }
}