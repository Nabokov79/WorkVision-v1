package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.DocumentEquipmentPassportMapper;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;
import ru.nabokovsg.laboratoryNK.repository.document.DocumentEquipmentPassportRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentPassportService;

@Service
@RequiredArgsConstructor
public class DocumentEquipmentPassportServiceImpl implements DocumentEquipmentPassportService {

    private final DocumentEquipmentPassportRepository repository;
    private final DocumentEquipmentPassportMapper mapper;
    private final EquipmentPassportService equipmentPassportService;

    @Override
    public void saveForSection(Section section, SurveyJournal surveyJournal) {
       repository.saveAll(
               equipmentPassportService.getAllById(surveyJournal.getEquipmentId())
                       .stream()
                       .filter(p -> p.getUseToProtocol().equals(false))
                       .map(p -> mapper.mapToDocumentEquipmentPassport(buildHeader(section.getSequentialNumber()
                                                                                 , p.getSequentialNumber())
                                                                                 , p.getMeaning()
                                                                                 , section))
                       .toList()
       );
    }

    private String buildHeader(Integer firstSequentialNumber, Integer secondSequentialNumber) {
        return String.join(".", String.valueOf(firstSequentialNumber), String.valueOf(secondSequentialNumber));
    }
}