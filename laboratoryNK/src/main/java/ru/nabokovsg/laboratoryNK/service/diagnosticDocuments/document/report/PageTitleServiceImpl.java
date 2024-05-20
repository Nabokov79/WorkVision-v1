package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report.PageTitleMapper;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.report.PageTitleRepository;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;
import ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document.DocumentHeaderService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PageTitleServiceImpl implements PageTitleService {

    private final PageTitleRepository repository;
    private final PageTitleMapper mapper;
    private final StringBuilderService builderService;
    private final DocumentHeaderService documentHeaderService;
    private final static String BOILER_ROOM = "котельная";

    @Override
    public PageTitle save(DiagnosticDocument document, String building
                        , LaboratoryEmployee chief, PageTitleTemplate pageTitleTemplate) {
        pageTitleTemplate.setEquipmentText(unitEquipmentText(pageTitleTemplate.getEquipmentText()
                                                           , document.getEquipmentDiagnosed()));
        pageTitleTemplate.setInstallationLocation(
                builderService.buildInstallationLocation(pageTitleTemplate.getInstallationLocation()
                                                       , building, BOILER_ROOM));
        PageTitle pageTitle = repository.save(mapper.mapToPageTitle(pageTitleTemplate
                , chief
                , builderService.numberAndDate(document.getDate(), document.getDocumentNumber())
                , String.valueOf(LocalDate.now().getYear())
                , building.split(",")[1]));
        documentHeaderService.saveForPageTitle(pageTitle, pageTitleTemplate.getDocumentHeaders());
        return pageTitle;
    }

    private String unitEquipmentText (String equipmentText, String equipmentDiagnosed) {
        return String.join(" ", equipmentText, equipmentDiagnosed.split(",")[1]);
    }
}