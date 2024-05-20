package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.AppendicesMapper;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.SurveyProtocol;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.AppendicesRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppendicesServiceImpl implements AppendicesService {

    private final AppendicesRepository repository;
    private final AppendicesMapper mapper;

    @Override
    public void saveForReport(Report report, Set<AppendicesTemplate> templates) {
        repository.saveAll(templates.stream()
                                    .map(a -> mapper.mapWithReport(a, report))
                                    .toList());
    }

    @Override
    public void saveForSurveyProtocol(SurveyProtocol protocol, Set<AppendicesTemplate> templates) {
        repository.saveAll(templates.stream()
                                    .map(a -> mapper.mapWithSurveyProtocol(a, protocol))
                                    .toList());
    }
}