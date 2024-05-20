package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.SurveyProtocol;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.ProtocolControl;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Report;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appendices")
public class Appendices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "appendices_name")
    private String appendicesName;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",  nullable = false)
    private Report report;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_protocol_id")
    private SurveyProtocol surveyProtocol;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_control_id")
    private ProtocolControl protocolControl;
}