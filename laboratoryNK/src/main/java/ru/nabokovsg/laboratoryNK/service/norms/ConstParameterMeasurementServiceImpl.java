package ru.nabokovsg.laboratoryNK.service.norms;

import org.springframework.stereotype.Component;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameterType;
import ru.nabokovsg.laboratoryNK.model.norms.UnitMeasurementType;

@Component
public class ConstParameterMeasurementServiceImpl implements ConstParameterMeasurementService {

    private static final String LENGTH = "длина";
    private static final String WIDTH = "ширина";
    private static final String HEIGHT = "высота";
    private static final String DEPTH = "глубина";
    private static final String DIAMETER = "диаметр";
    private static final String SQUARE = "площадь";
    private static final String QUANTITY = "количество";
    private static final String MM = "мм";
    private static final String M_2 = "м2";
    private static final String MM_2 = "мм2";
    private static final String PIECES = "шт";

    @Override
    public String getMeasuredParameter(String measuredParameter) {
        switch (convertToParameterMeasurement(measuredParameter)) {
            case LENGTH -> {return LENGTH;}
            case WIDTH -> {return WIDTH;}
            case HEIGHT -> {return HEIGHT;}
            case DEPTH -> {return DEPTH;}
            case DIAMETER -> {return DIAMETER;}
            case SQUARE -> {return SQUARE;}
            case QUANTITY -> {return QUANTITY;}
            default -> throw new BadRequestException(
                    String.format("ParameterMeasurement=%s is not supported", measuredParameter));
        }
    }

    @Override
    public String getUnitMeasurement(String unitMeasurement) {
        switch (convertToUnitMeasurement(unitMeasurement)) {
            case MM -> {return MM;}
            case M_2 -> {return M_2;}
            case MM_2 -> {return MM_2;}
            case PIECES -> {return PIECES;}
            default -> throw new BadRequestException(
                    String.format("UnitMeasurement=%s is not supported", unitMeasurement));
        }
    }

    public MeasuredParameterType convertToParameterMeasurement(String measuredParameter) {
        return MeasuredParameterType.from(measuredParameter).orElseThrow(
                () -> new BadRequestException(String.format("Unknown parameterMeasurement=%s", measuredParameter)));
    }

    public UnitMeasurementType convertToUnitMeasurement(String unitMeasurement) {
        return UnitMeasurementType.from(unitMeasurement).orElseThrow(
                () -> new BadRequestException(String.format("Unknown unitMeasurement=%s", unitMeasurement)));
    }
}