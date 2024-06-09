package ru.nabokovsg.laboratoryNK.service.vms.norm;

import ru.nabokovsg.laboratoryNK.dto.vms.norm.measuredParameter.MeasuredParameterDto;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.vms.norm.MeasuredParameter;

import java.util.List;
import java.util.Set;

public interface MeasuredParameterService {

    Set<MeasuredParameter> saveForDefect(Defect defect, List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> saveForElementRepair(ElementRepair repair, List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> update(List<MeasuredParameterDto> parametersDto);

    Set<MeasuredParameter> getAllByDefectId(Long defectId);
}