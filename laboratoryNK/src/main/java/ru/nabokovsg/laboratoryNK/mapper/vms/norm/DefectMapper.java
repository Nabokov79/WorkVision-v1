package ru.nabokovsg.laboratoryNK.mapper.vms.norm;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;

@Mapper(componentModel = "spring")
public interface DefectMapper {

    @Mapping(source = "defectDto.id", target = "id")
    @Mapping(source = "defectDto.defectName", target = "defectName")
    @Mapping(source = "typeCalculation", target = "typeCalculation")
    @Mapping(source = "defectDto.notMeetRequirements", target = "notMeetRequirements")
    @Mapping(source = "defectDto.useCalculateThickness", target = "useCalculateThickness")
    Defect mapToDefect(DefectDto defectDto, CalculationType typeCalculation);

    ResponseDefectDto mapToResponseDefectDto(Defect defect);

    ResponseShortDefectDto mapToResponseShortDefectDto(Defect defect);
}