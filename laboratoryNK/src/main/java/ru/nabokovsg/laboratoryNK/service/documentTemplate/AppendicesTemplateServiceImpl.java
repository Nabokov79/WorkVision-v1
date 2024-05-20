package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.AppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.appendices.ResponseAppendicesTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.AppendicesTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ReportTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.AppendicesTemplateRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppendicesTemplateServiceImpl implements AppendicesTemplateService {

    private final AppendicesTemplateRepository repository;
    private final AppendicesTemplateMapper mapper;
    private final static String NAME_OF_LIST = "Приложения";

    @Override
    public ResponseAppendicesTemplateDto save(AppendicesTemplateDto appendicesDto) {
        return mapper.mapToResponseAppendicesDto(
                Objects.requireNonNullElseGet(repository.findByAppendicesName(appendicesDto.getAppendicesName())
                        , () -> repository.save(mapper.mapToAppendicesTemplate(appendicesDto, NAME_OF_LIST))));
    }

    @Override
    public ResponseAppendicesTemplateDto update(AppendicesTemplateDto appendicesDto) {
        if (repository.existsById(appendicesDto.getId())) {
            return mapper.mapToResponseAppendicesDto(
                          repository.save(mapper.mapToAppendicesTemplate(appendicesDto, NAME_OF_LIST)));
        }
        throw new NotFoundException(
                String.format("Appendices template with id=%s not found for update", appendicesDto.getId())
        );
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Appendices template with id=%s not found for delete", id));
    }

    @Override
    public void addReportTemplate(ReportTemplate reportTemplate) {
        repository.save(
                mapper.mapWithReportTemplate(getByEquipmentTypeId(reportTemplate.getEquipmentTypeId()), reportTemplate)
        );
    }

    @Override
    public void addProtocolTemplate(SurveyProtocolTemplate protocolTemplate) {
        repository.save(
          mapper.mapWithProtocolTemplate(getByEquipmentTypeId(protocolTemplate.getEquipmentTypeId()), protocolTemplate)
        );
    }

    private AppendicesTemplate getByEquipmentTypeId(Long equipmentTypeId) {
        return repository.findByEquipmentTypeId(equipmentTypeId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Appendices template with equipmentType id=%s not found for delete"
                                                                                                   , equipmentTypeId)));
    }
}