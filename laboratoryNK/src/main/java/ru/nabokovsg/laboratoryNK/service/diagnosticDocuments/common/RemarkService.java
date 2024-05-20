package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.common;

import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.RemarkDto;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.ResponseRemarkDto;

import java.util.List;

public interface RemarkService {

    ResponseRemarkDto save(RemarkDto remarkDto);

    ResponseRemarkDto update(RemarkDto remarkDto);

    List<ResponseRemarkDto> getAll(Long id);

    void delete(Long id);
}