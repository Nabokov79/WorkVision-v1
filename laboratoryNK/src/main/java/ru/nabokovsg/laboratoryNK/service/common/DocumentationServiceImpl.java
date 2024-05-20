package ru.nabokovsg.laboratoryNK.service.common;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentation.DocumentationDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.common.DocumentationMapper;
import ru.nabokovsg.laboratoryNK.model.common.QDocumentation;
import ru.nabokovsg.laboratoryNK.repository.common.DocumentationRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DocumentationServiceImpl implements DocumentationService {

    private final DocumentationRepository repository;
    private final DocumentationMapper mapper;
    private final EntityManager em;

    @Override
    public DocumentationDto save(DocumentationDto documentationDto) {
        return mapper.mapToDocumentationDto(
                Objects.requireNonNullElseGet(repository.findByTitle(documentationDto.getTitle())
                        , () -> repository.save(mapper.mapToDocumentation(documentationDto)))
        );
    }

    @Override
    public DocumentationDto update(DocumentationDto documentationDto) {
        if (repository.existsById(documentationDto.getId())) {
            return mapper.mapToDocumentationDto(repository.save(mapper.mapToDocumentation(documentationDto)));
        }
        throw new NotFoundException(
                String.format("Documentation with id=%s not found for update", documentationDto.getId())
        );
    }

    @Override
    public List<DocumentationDto> getAll(List<Long> ids, String number, String title) {
        QDocumentation documentation = QDocumentation.documentation;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (ids != null && !ids.isEmpty()) {
            booleanBuilder.and(documentation.id.in(ids));
        }
        if (number != null) {
            booleanBuilder.and(documentation.number.eq(number));
        }
        if (title != null) {
            booleanBuilder.and(documentation.title.eq(title));
        }
        return new JPAQueryFactory(em).from(documentation)
                .select(documentation)
                .where(booleanBuilder)
                .fetch()
                .stream()
                .map(mapper::mapToDocumentationDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Documentation with id=%s not found for delete", id));
    }
}