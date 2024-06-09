package ru.nabokovsg.laboratoryNK.model.vms.norm;

import java.util.Optional;

public enum MeasuredParameterType {

    LENGTH,
    WIDTH,
    HEIGHT,
    DEPTH,
    DIAMETER,
    SQUARE,
    QUANTITY,
    DIRECTION;

    public static Optional<MeasuredParameterType> from(String parameter) {
        for (MeasuredParameterType type : values()) {
            if (type.name().equalsIgnoreCase(parameter)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}