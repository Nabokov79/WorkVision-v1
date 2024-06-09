package ru.nabokovsg.laboratoryNK.model.vms.norm;

import java.util.Optional;

public enum CalculationType {

    QUANTITY,
    SQUARE,
    MAX,
    MIN,
    MAX_MIN,
    NO_ACTION,
    DIRECTION;

    public static Optional<CalculationType> from(String calculation) {
        for (CalculationType type : values()) {
            if (type.name().equalsIgnoreCase(calculation)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}