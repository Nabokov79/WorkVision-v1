package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.DocumentMeasuringToolMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.DocumentMeasuringToolRepository;
import ru.nabokovsg.laboratoryNK.service.common.MeasuringToolService;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentMeasuringToolServiceImpl implements DocumentMeasuringToolService {

    private final DocumentMeasuringToolRepository repository;
    private final DocumentMeasuringToolMapper mapper;
    private final MeasuringToolService measuringToolService;
    private final StringBuilderService stringBuilderService;

    @Override
    public void save(Subsection subsection, Set<LaboratoryEmployee> employees
                                          , List<MeasuringToolTemplate> measuringToolsTemplates) {
        List<MeasuringTool> measuringTools = measuringToolService.getByLaboratoryEmployeeDataAndTemplate(employees
                                                                                             , measuringToolsTemplates);
        if (measuringTools.isEmpty()) {
            repository.saveAll(measuringToolsTemplates.stream()
                                                      .map(t -> mapper.mapFromMeasuringToolTemplate(t, subsection))
                                                      .toList());
        } else {
            Map<String, Integer> sequentialNumbers = measuringToolsTemplates.stream().collect(Collectors.toMap(MeasuringToolTemplate::getToll, MeasuringToolTemplate::getSequentialNumber));
            repository.saveAll(measuringTools.stream()
                    .map(t -> mapper.mapToDocumentMeasuringTool(sequentialNumbers.get(t.getToll())
                                                              , stringBuilderService.buildMeasuringToolTemplate(t)
                                                              , subsection))
                    .toList());
        }
    }
}