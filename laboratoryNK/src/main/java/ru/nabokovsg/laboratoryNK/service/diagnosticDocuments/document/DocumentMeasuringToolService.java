package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;

import java.util.List;
import java.util.Set;

public interface DocumentMeasuringToolService {

    void save(Subsection subsection
            , Set<LaboratoryEmployee> employees
            , List<MeasuringToolTemplate> measuringToolsTemplates);
}
