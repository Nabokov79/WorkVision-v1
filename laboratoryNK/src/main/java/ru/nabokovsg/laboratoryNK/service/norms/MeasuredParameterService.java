package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.measuredParameter.MeasuredParameterDto;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;

import java.util.List;
import java.util.Set;

public interface MeasuredParameterService {

    Set<MeasuredParameter> saveForDefect(Defect defect, List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> saveForElementRepair(ElementRepair repair, List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> update(List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> getAllByDefectId(Long defectId);
}