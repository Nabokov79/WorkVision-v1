package ru.nabokovsg.laboratoryNK.model.vms.norm;

import java.util.Optional;

public enum DefectDirection {

    UP,
    DOWN,
    RIGHT,
    LEFT,
    NOT;

    public static Optional<DefectDirection> from(String direction) {
        for (DefectDirection type : values()) {
            if (type.name().equalsIgnoreCase(direction)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}