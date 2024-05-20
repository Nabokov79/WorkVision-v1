package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.standardSize.StandardSizeDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.StandardSize;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;

public interface StandardSizeService {

    StandardSize save(StandardSizeDto standardSizeDto);

    StandardSize update(StandardSizeDto standardSizeDto);

    StandardSize getByPredicate(ResultUltrasonicThicknessMeasurement measurement);
}