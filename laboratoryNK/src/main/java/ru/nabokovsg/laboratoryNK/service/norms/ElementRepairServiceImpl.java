package ru.nabokovsg.laboratoryNK.service.norms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseShortElementRepairDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.norms.ElementRepairMapper;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;
import ru.nabokovsg.laboratoryNK.repository.norms.ElementRepairRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementRepairServiceImpl implements ElementRepairService {

    private final ElementRepairRepository repository;
    private final ElementRepairMapper mapper;
    private final MeasuredParameterService parameterService;


    @Override
    public ResponseElementRepairDto save(ElementRepairDto repairDto) {
        ElementRepair repair = repository.findByRepairName(repairDto.getRepairName());
        if (repair == null) {
            repair = repository.save(mapper.mapToElementRepair(repairDto
                                                        , getTypeCalculation(repairDto.getTypeCalculation())));
            repair.setMeasuredParameters(
                                    parameterService.saveForElementRepair(repair, repairDto.getMeasuredParameters()));
        }
        return mapper.mapToResponseElementRepairDto(repair);
    }

    @Override
    public ResponseElementRepairDto update(ElementRepairDto repairDto) {
        if (repository.existsById(repairDto.getId())) {
            ElementRepair repair =  repository.save(mapper.mapToElementRepair(repairDto
                                                                , getTypeCalculation(repairDto.getTypeCalculation())));
            repair.setMeasuredParameters(parameterService.update(repairDto.getMeasuredParameters()));
            return mapper.mapToResponseElementRepairDto(repair);
        }
        throw new NotFoundException(String.format("Repair method with id=%s not found for update", repairDto.getId()));
    }

    @Override
    public ResponseElementRepairDto get(Long id) {
        return mapper.mapToResponseElementRepairDto(getById(id));
    }

    @Override
    public List<ResponseShortElementRepairDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseShortElementRepairDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Repair method with id=%s not found for delete", id));
    }

    @Override
    public ElementRepair getById(Long id) {
        if (id != null) {
            return repository.findById(id)
                    .orElseThrow(() -> new NotFoundException(String.format("Repair method with id=%s not found", id)));
        }
        return null;
    }

    private TypeCalculation getTypeCalculation(String calculation) {
        return TypeCalculation.from(calculation).orElseThrow(
                () -> new BadRequestException(
                        String.format("Unsupported element repair calculation type=%s", calculation)));
    }
}