package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_protocols")
public class SurveyProtocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_diagnosed_id")
    private Long equipmentDiagnosedId;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @OneToMany(mappedBy = "surveyProtocol", fetch = FetchType.LAZY)
    private Set<DocumentHeader> leftHeader;
    @OneToOne
    @JoinColumn(name = "work_place_id", referencedColumnName = "id")
    private Workplace workPlace;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @OneToMany(mappedBy = "surveyProtocol", fetch = FetchType.LAZY)
    private Set<Subsection> subsections;
    @OneToMany(mappedBy = "surveyProtocol", fetch = FetchType.LAZY)
    private Set<DocumentTable> tables;
    @OneToOne
    @JoinColumn(name = "conclusion_id", referencedColumnName = "id")
    private Conclusion conclusion;
    @OneToMany(mappedBy = "surveyProtocol", fetch = FetchType.LAZY)
    private Set<Appendices> appendices;
}