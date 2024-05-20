package ru.nabokovsg.laboratoryNK.repository.diagnosticDocuments;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.Remark;

import java.util.Set;

public interface RemarkRepository extends JpaRepository<Remark, Long> {

    Remark findByDocumentIdAndRemark(Long documentId, String remark);

    Integer countAllByDocumentId(Long documentId);

    Set<Remark> findAllByEmployeeId(Long employeeId);
}