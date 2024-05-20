package ru.nabokovsg.laboratoryNK.model.norms;

import java.util.Optional;

public enum TypeCalculation {

    QUANTITY,
    SQUARE,
    NO_ACTION;

    public static Optional<TypeCalculation> from(String calculation) {
        for (TypeCalculation type : values()) {
            if (type.name().equalsIgnoreCase(calculation)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}