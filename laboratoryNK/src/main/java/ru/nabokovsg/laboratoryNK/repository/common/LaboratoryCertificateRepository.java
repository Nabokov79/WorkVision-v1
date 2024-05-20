package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryCertificate;

public interface LaboratoryCertificateRepository extends JpaRepository<LaboratoryCertificate, Long> {

    LaboratoryCertificate findByDocumentTypeAndLicenseNumberAndOrganization(String documentType
                                                                          , String licenseNumber
                                                                          , String organization);
}