package ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed;

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
@Table(name = "parts_elements")
public class PartElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "part_name")
    private String partName;
    @OneToOne
    @JoinColumn(name = "standard_size_id", referencedColumnName = "id")
    private StandardSize standardSize;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id",  nullable = false)
    private EquipmentElement element;
}