package ru.nabokovsg.laboratoryNK.model.norms;

import java.util.Optional;

public enum TypeOfParameterCalculation {

    MAX,
    MIN,
    MAX_MIN,
    NO_ACTION;

    public static Optional<TypeOfParameterCalculation> from(String calculation) {
        for (TypeOfParameterCalculation type : values()) {
            if (type.name().equalsIgnoreCase(calculation)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}