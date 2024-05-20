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
@Table(name = "equipment_types")
public class EquipmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "model")
    private String model;
}