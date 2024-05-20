package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diagnostic_documents")
public class DiagnosticDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @Column(name = "equipment_diagnosed")
    private String equipmentDiagnosed;
    @Column(name = "equipment_diagnosed_id")
    private Long equipmentDiagnosedId;
    @Column(name = "diagnostic_document_type")
    private String documentType;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnostic_document_type_id",  nullable = false)
    private DiagnosticDocumentType diagnosticDocumentType;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "installation_location")
    private String installationLocation;
    @Column(name = "next_date")
    private LocalDate nextDate;
    @Column(name = "document_number")
    private Integer documentNumber;
    @Column(name = "document_status")
    private String documentStatus;
    @Column(name = "drawing_status")
    private String drawingStatus;
    @Column(name = "document_path")
    private String documentPath;
    @Column(name = "drawing_path")
    private String drawingPath;
}