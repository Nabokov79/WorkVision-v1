package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.AcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableThickness.ResponseAcceptableThicknessDto;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableThickness;

import java.util.List;

public interface AcceptableThicknessService {

    ResponseAcceptableThicknessDto save(AcceptableThicknessDto thicknessDto);

    ResponseAcceptableThicknessDto update(AcceptableThicknessDto thicknessDto);

    List<ResponseAcceptableThicknessDto> getAll(Long id);

    void delete(Long id);

    AcceptableThickness getAcceptableThickness(UltrasonicThicknessMeasurement measurement);
}