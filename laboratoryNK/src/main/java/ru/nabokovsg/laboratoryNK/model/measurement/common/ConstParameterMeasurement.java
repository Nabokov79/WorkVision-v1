package ru.nabokovsg.laboratoryNK.model.measurement.common;

public class ConstParameterMeasurement {

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

    public static String getLength(){
        return LENGTH;
    }

    public static String getWidth() {
        return WIDTH;
    }

    public static String getHeight() {
        return HEIGHT;
    }

    public static String getDepth() {
        return DEPTH;
    }

    public static String getDiameter() {
        return DIAMETER;
    }

    public static String getSquare() {
        return SQUARE;
    }

    public static String getQuantity() {
        return QUANTITY;
    }

    public static String getMm() {
        return MM;
    }

    public static String getM2() {
        return M_2;
    }

    public static String getMm2() {
        return MM_2;
    }

    public static String getPieces() {
        return PIECES;
    }
}
