package ru.nabokovsg.laboratoryNK.mapper.measurement.utm;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;

@Mapper(componentModel = "spring")
public interface ResultUltrasonicThicknessMeasurementServiceMapper {

    ResultUltrasonicThicknessMeasurement mapToCalculatingUltrasonicThicknessMeasurement(
                                                                            UltrasonicThicknessMeasurement measurement);
}