package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Appendices;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_diagnosed_id")
    private Long equipmentDiagnosedId;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @OneToOne
    @JoinColumn(name = "page_title_id", referencedColumnName = "id")
    private PageTitle pageTitle;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private Set<Section> sections;
    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY)
    private Set<Appendices> appendices;
}