package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conclusion")
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "positive_text")
    private String positiveText;
    @Column(name = "negative_text")
    private String negativeText;
}
