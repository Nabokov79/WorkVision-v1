package ru.nabokovsg.laboratoryNK.model.documentTemplate;

import java.util.Optional;

public enum ColumnHeaderType {

    STRING_NUMBER,
    DATE,
    SURVEYS_DESCRIPTION,
    ORGANIZATION_NAME,
    REPAIR_DESCRIPTION,
    DOCUMENT_NUMBER,
    ELEMENT,
    PART_ELEMENT,
    DEFECTS,
    REPAIR_ELEMENT,
    VISUAL_INSPECTION,
    DESIGN_THICKNESS,
    MEASURED_THICKNESS,
    MAX_CORROSION,
    RESIDUAL_THICKNESS,
    MIN_ALLOWABLE_THICKNESS,
    PLACE_NUMBER,
    HEIGHT,
    DEVIATION,
    PRECIPITATION,
    DIFFERENCE_NEIGHBORING_POINTS,
    DIFFERENCE_DIAMETRICAL_POINTS,
    DIAMETER,
    HARDNESS;

    public static Optional<ColumnHeaderType> from(String columnHeaderType) {
        for (ColumnHeaderType type : values()) {
            if (type.name().equalsIgnoreCase(columnHeaderType)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}