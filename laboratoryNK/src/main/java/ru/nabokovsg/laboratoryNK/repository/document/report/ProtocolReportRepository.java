package ru.nabokovsg.laboratoryNK.repository.document.report;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;

public interface ProtocolReportRepository extends JpaRepository<ProtocolReport, Long> {
}