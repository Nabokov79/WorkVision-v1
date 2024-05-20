package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.TableMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableType;
import ru.nabokovsg.laboratoryNK.repository.document.TableRepository;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository repository;
    private final TableMapper mapper;
    private final CellTableService cellTableService;

    @Override
    public DocumentTable savaForSubsection(TableTemplate tableTemplate, SurveyJournal surveyJournal) {
        DocumentTable table = repository.save(mapper.mapToDocumentTable(tableTemplate));
        common(table, tableTemplate, surveyJournal);
        return table;
    }

    @Override
    public void savaForProtocolReport(ProtocolReport protocol, Set<TableTemplate> templates, SurveyJournal surveyJournal) {
        Map<Integer, TableTemplate> tableTemplates = templates.stream()
                                                 .collect(Collectors.toMap(TableTemplate::getSequentialNumber, t -> t));
        repository.saveAll(templates.stream()
                                    .map(t -> mapper.mapWithProtocolReport(t, protocol))
                                    .toList())
                .forEach(t -> common(t
                                   , tableTemplates.get(t.getSequentialNumber())
                                   , surveyJournal));
    }

    private void common(DocumentTable table, TableTemplate template, SurveyJournal surveyJournal) {
        TableType type = TableType.valueOf(template.getTableType());
        switch (type) {
            case SURVEYS_TABLE -> cellTableService.saveEquipmentInspection(table, template, surveyJournal);
            case REPAIR_TABLE -> cellTableService.saveEquipmentRepair(table, template, surveyJournal);
            case TABLE_VMS -> cellTableService.saveVMSMeasurement(table, template, surveyJournal);
            case TABLE_VMC -> cellTableService.saveEquipmentRepair(table, template, surveyJournal);
            case TABLE_UM -> cellTableService.saveUTMeasurement(table, template, surveyJournal);
            case MEASUREMENT_RP -> cellTableService.saveReferencePointMeasurement(table, template, surveyJournal);
            case MEASUREMENT_CP -> cellTableService.saveControlPointMeasurement(table, template, surveyJournal);
            case TABLE_UC -> cellTableService.saveEquipmentRepair(table, template, surveyJournal);
            case MEASUREMENT_HARDNESS -> cellTableService.saveHardnessMeasurement(table, template, surveyJournal);
        }
    }
}