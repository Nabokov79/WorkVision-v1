package ru.nabokovsg.laboratoryNK.service.vms.common;

import org.springframework.stereotype.Component;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.model.vms.norm.DefectDirection;

@Component
public class ConstDefectDirectionServiceImpl implements ConstDefectDirectionService {

    private static final String UP = "вверх";
    private static final String DOWN = "вниз";
    private static final String RIGHT = "вправо";
    private static final String LEFT = "влево";

    @Override
    public String get(String direction) {
        switch (convertToDefectDirection(direction)) {
            case UP -> {
                return UP;
            }
            case DOWN -> {
                return DOWN;
            }
            case RIGHT -> {
                return RIGHT;
            }
            case LEFT -> {
                return LEFT;
            }
            default -> throw new BadRequestException(
                    String.format("ParameterMeasurement=%s is not supported", direction));
        }
    }

    public DefectDirection convertToDefectDirection(String direction) {
        return DefectDirection.from(direction).orElseThrow(
                () -> new BadRequestException(String.format("Unknown direction=%s", direction)));
    }
}