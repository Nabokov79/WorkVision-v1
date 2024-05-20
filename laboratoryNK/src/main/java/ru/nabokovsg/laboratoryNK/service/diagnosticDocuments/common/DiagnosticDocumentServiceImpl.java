package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.DiagnosticDocumentDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.common.DiagnosticDocumentMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DocumentStatus;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.QDiagnosticDocument;
import ru.nabokovsg.laboratoryNK.repository.diagnosticDocuments.DiagnosticDocumentRepository;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;
import ru.nabokovsg.laboratoryNK.service.common.SurveyJournalService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.protocol.ProtocolControlService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.protocol.SurveyProtocolService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report.ReportService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@RequiredArgsConstructor
public class DiagnosticDocumentServiceImpl extends DiagnosticDocumentStatusFactory implements DiagnosticDocumentService {

    private final DiagnosticDocumentRepository repository;
    private final DiagnosticDocumentMapper mapper;
    private final EntityManager em;
    private final DiagnosticDocumentTypeService documentTypeService;
    private final ReportService reportService;
    private final ProtocolControlService protocolControlService;
    private final SurveyProtocolService surveyProtocolService;
    private final SurveyJournalService surveyJournalService;
    private final StringBuilderService builderService;

    @Override
    public void save(SurveyJournalDto journalDto, ResponseSurveyJournalDto journal) {
        if (journal.getDate() != null) {
            DiagnosticDocumentType diagnosticDocumentType = documentTypeService.getById(
                                                                              journalDto.getDiagnosticDocumentTypeId());
            repository.save(
                    mapper.mapToDiagnosticDocument(journal
                            , journalDto.getEquipmentId()
                            , diagnosticDocumentType
                            , builderService.buildDiagnosticDocumentType(diagnosticDocumentType)
                            , getDiagnosticDocumentStatus(DocumentStatus.NEW_DOCUMENT)
                            , getDrawingStatus(DocumentStatus.NEW_DRAWING, journalDto.isDrawing())
                    , getDocumentNumber())
            );
        }
    }

    @Override
    public void update(SurveyJournalDto journalDto, ResponseSurveyJournalDto journal) {
        DiagnosticDocument document = repository.findBySurveyJournalId(journal.getId());
        if (document != null) {
            if (document.getDocumentStatus().equals(getDiagnosticDocumentStatus(DocumentStatus.NEW_DOCUMENT))) {
                DiagnosticDocumentType diagnosticDocumentType = documentTypeService.getById(
                                                                              journalDto.getDiagnosticDocumentTypeId());
                repository.save(
                        mapper.mapToUpdateDiagnosticDocument(document
                                                , journal
                                                , journalDto.getEquipmentId()
                                                , diagnosticDocumentType
                                                , builderService.buildDiagnosticDocumentType(diagnosticDocumentType))
                );
            }
        } else {
            save(journalDto, journal);
        }
    }

    @Override
    public List<DiagnosticDocumentDto> getAll(LocalDate startPeriod, LocalDate endPeriod, boolean week, boolean month) {
        QDiagnosticDocument document = QDiagnosticDocument.diagnosticDocument;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (startPeriod == null || endPeriod == null) {
            LocalDate now = LocalDate.now();
            if (week) {
                startPeriod = now.with(now.with(DayOfWeek.MONDAY).minusDays(1));
                endPeriod = now.with(now.with(DayOfWeek.SUNDAY).plusDays(1));
            }
            if (month) {
                startPeriod = now.with(ChronoField.DAY_OF_MONTH, 1).minusDays(1);
                endPeriod = now.with(ChronoField.DAY_OF_MONTH, now.lengthOfMonth()).plusDays(1);
            }
            if (!month && !week) {
                startPeriod = now.with(ChronoField.DAY_OF_MONTH, 1).minusDays(1);
                endPeriod = now.with(ChronoField.DAY_OF_MONTH, now.lengthOfMonth()).plusDays(1);
            }
        }
        booleanBuilder.and(document.date.after(startPeriod));
        booleanBuilder.and(document.date.before(endPeriod));
        return new JPAQueryFactory(em).from(document)
                .select(document)
                .where(booleanBuilder)
                .fetch()
                .stream()
                .map(mapper::mapToDiagnosticDocumentDto)
                .toList();
    }

    @Override
    public String create(Long id) {
        DiagnosticDocument document = repository.findBySurveyJournalId(id);
        if (document == null) {
            return String.format("DiagnosticDocument for surveyJournalId=%s not found",id);
        }
        SurveyJournal surveyJournal = surveyJournalService.getById(document.getSurveyJournalId());
        switch (document.getDiagnosticDocumentType().getTypeDocument()) {
            case REPORT -> reportService.save(surveyJournal, document);
            case SURVEY_PROTOCOL -> surveyProtocolService.save(surveyJournal, document);
            case CONTROL_PROTOCOL -> protocolControlService.save(surveyJournal, document);
        }
        return String.join(" ", "Создан"
                , document.getDocumentType()
                , "№" , String.valueOf(document.getDocumentNumber())
                , "от", String.valueOf(document.getDate()));
    }

    @Override
    public void validateByStatus(Long surveyJournalId) {
        DiagnosticDocument document = repository.findBySurveyJournalId(surveyJournalId);
        if (document.getDocumentStatus().equals(getDiagnosticDocumentStatus(DocumentStatus.NEW_DOCUMENT))) {
            throw new BadRequestException(
                    String.format("Document has been created for writing in the task log with number=%s"
                                                                                        , document.getDocumentNumber())
            );
        }
    }

    @Override
    public DiagnosticDocument getById(Long id) {
        return repository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format("Diagnostic document with id=%s not found", id)));
    }

    @Override
    public void updateStatus(DiagnosticDocument document, DocumentStatus status) {
        document.setDocumentStatus(getDiagnosticDocumentStatus(status));
        repository.save(document);
    }

    private Integer getDocumentNumber() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear());
        QDiagnosticDocument document = QDiagnosticDocument.diagnosticDocument;
        Integer number = new JPAQueryFactory(em).from(document)
                .select(document.documentNumber.max())
                .where(document.date.after(firstDay).and(document.date.before(lastDay)))
                .fetchOne();
        if (number == null) {
            return 1;
        }
        return (++number);
    }
}