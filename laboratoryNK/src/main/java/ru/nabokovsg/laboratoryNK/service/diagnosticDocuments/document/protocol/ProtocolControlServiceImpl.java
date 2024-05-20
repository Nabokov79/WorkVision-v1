package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.protocol;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.protocol.ProtocolControlMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.repository.document.protocol.ProtocolControlRepository;

@Service
@RequiredArgsConstructor
public class ProtocolControlServiceImpl implements ProtocolControlService {

    private final ProtocolControlRepository repository;
    private final ProtocolControlMapper mapper;

    @Override
    public void save(SurveyJournal surveyJournal, DiagnosticDocument document) {

    }
}