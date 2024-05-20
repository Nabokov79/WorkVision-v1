package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.laboratoryCertificate.LaboratoryCertificateDto;

import java.util.List;

public interface LaboratoryCertificateService {

    LaboratoryCertificateDto save(LaboratoryCertificateDto certificateDto);

    LaboratoryCertificateDto update(LaboratoryCertificateDto certificateDto);

    List<LaboratoryCertificateDto> getAll();

    void delete(Long id);
}