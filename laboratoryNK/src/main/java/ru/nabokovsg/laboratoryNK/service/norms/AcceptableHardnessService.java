package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.AcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.dto.norms.acceptableHardness.ResponseAcceptableHardnessDto;
import ru.nabokovsg.laboratoryNK.model.measurement.HardnessMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableHardness;

import java.util.List;

public interface AcceptableHardnessService {

    ResponseAcceptableHardnessDto save(AcceptableHardnessDto hardnessDto);

    ResponseAcceptableHardnessDto update(AcceptableHardnessDto hardnessDto);

    List<ResponseAcceptableHardnessDto> getAll(Long id);

    void delete(Long id);

    AcceptableHardness getByPredicate(HardnessMeasurement measurement);
}