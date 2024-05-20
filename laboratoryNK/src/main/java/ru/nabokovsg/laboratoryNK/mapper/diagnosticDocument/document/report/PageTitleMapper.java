package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.PageTitle;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;

@Mapper(componentModel = "spring")
public interface PageTitleMapper {

    @Mapping(target = "id", ignore = true)
    PageTitle mapToPageTitle(PageTitleTemplate pageTitleTemplate
                           , LaboratoryEmployee chief
                           , String numberAndDate
                           , String year
                           , String address);
}