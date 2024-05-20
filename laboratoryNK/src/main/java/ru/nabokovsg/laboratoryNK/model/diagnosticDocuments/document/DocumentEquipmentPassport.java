package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "document_equipment_passport")
public class DocumentEquipmentPassport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "header")
    private String header;
    @Column(name = "meaning")
    private String meaning;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id",  nullable = false)
    private Section section;
}
