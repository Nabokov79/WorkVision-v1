package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.ConclusionTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.ConclusionTemplateRepository;

@Service
@RequiredArgsConstructor
public class ConclusionTemplateServiceImpl implements ConclusionTemplateService {

    private final ConclusionTemplateRepository repository;
    private final ConclusionTemplateMapper mapper;

    @Override
    public ResponseConclusionTemplateDto save(ConclusionTemplateDto conclusionDto) {
        if (repository.existsByDocumentTypeId(conclusionDto.getDocumentTypeId())) {
            throw new BadRequestException(
                    String.format("Conclusion template with documentTypeId=%s is found"
                                                                        , conclusionDto.getDocumentTypeId()));
        }
        return mapper.mapToResponseConclusionTemplateDto(
                repository.save(mapper.mapToConclusionTemplate(conclusionDto)));
    }

    @Override
    public ResponseConclusionTemplateDto update(ConclusionTemplateDto conclusionDto) {
        if (repository.existsById(conclusionDto.getId())) {
            return mapper.mapToResponseConclusionTemplateDto(
                    repository.save(mapper.mapToConclusionTemplate(conclusionDto)));
        }
       throw new NotFoundException(String.format("Conclusion template with id=%s not found", conclusionDto.getId()));
    }

    @Override
    public ResponseConclusionTemplateDto get(Long id) {
        return mapper.mapToResponseConclusionTemplateDto(
                repository.findById(id).orElseThrow(
                        () -> new NotFoundException(String.format("Conclusion template with id=%s not found", id))));
    }

    @Override
    public ConclusionTemplate getByDocumentTypeId(Long documentTypeId) {
        return repository.findByDocumentTypeId(documentTypeId).orElseThrow(() -> new NotFoundException(
                String.format("Conclusion template with documentTypeId=%s not found", documentTypeId)));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Conclusion template with id=%s not found for delete", id));
    }
}