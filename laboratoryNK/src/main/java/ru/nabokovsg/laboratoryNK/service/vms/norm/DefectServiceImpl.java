package ru.nabokovsg.laboratoryNK.service.vms.norm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.DefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseDefectDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.defects.ResponseShortDefectDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.vms.norm.DefectMapper;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.CalculationType;
import ru.nabokovsg.laboratoryNK.repository.vms.norm.DefectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepository repository;
    private final DefectMapper mapper;
    private final MeasuredParameterService parameterService;

    @Override
    public ResponseDefectDto save(DefectDto defectDto) {
        Defect defect = repository.findByDefectName(defectDto.getDefectName());
        if (defect == null) {
            defect = repository.save(mapper.mapToDefect(defectDto
                                                      , getTypeCalculation(defectDto.getTypeCalculation())));
            defect.setMeasuredParameters(parameterService.saveForDefect(defect, defectDto.getMeasuredParameters()));
        }
        return mapper.mapToResponseDefectDto(defect);
    }

    @Override
    public ResponseDefectDto update(DefectDto defectDto) {
        if (repository.existsById(defectDto.getId())) {
            Defect defect = repository.save(mapper.mapToDefect(defectDto
                                                           , getTypeCalculation(defectDto.getTypeCalculation())));
            defect.setMeasuredParameters(parameterService.saveForDefect(defect, defectDto.getMeasuredParameters()));
            return mapper.mapToResponseDefectDto(defect);
        }
        throw new NotFoundException(String.format("Defect with id=%s not found for update", defectDto.getId()));
    }

    @Override
    public ResponseDefectDto get(Long id) {
        return mapper.mapToResponseDefectDto(getById(id));
    }

    @Override
    public List<ResponseShortDefectDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseShortDefectDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Defect with id=%s not found for delete", id));
    }

    @Override
    public Defect getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Defect with id=%s not found", id)));
    }

    private CalculationType getTypeCalculation(String calculation) {
        return CalculationType.from(calculation).orElseThrow(
                () -> new BadRequestException(String.format("Unsupported defect calculation type=%s", calculation)));
    }
}