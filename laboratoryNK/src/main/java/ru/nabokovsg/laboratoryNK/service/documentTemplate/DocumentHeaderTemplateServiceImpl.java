package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.client.LaboratoryClient;
import ru.nabokovsg.laboratoryNK.dto.client.DivisionDto;
import ru.nabokovsg.laboratoryNK.dto.client.EmployeeDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.DocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.laboratoryCertificate.LaboratoryCertificateDto;
import ru.nabokovsg.laboratoryNK.exceptions.BadRequestException;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.DocumentHeaderTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DivisionType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.DocumentHeaderTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.common.LaboratoryCertificateService;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentHeaderTemplateServiceImpl implements DocumentHeaderTemplateService {

    private final DocumentHeaderTemplateRepository repository;
    private final DocumentHeaderTemplateMapper mapper;
    private final StringBuilderService stringBuilderService;
    private final LaboratoryClient client;
    private final LaboratoryCertificateService laboratoryCertificateService;

    @Override
    public ResponseDocumentHeaderTemplateDto save(DocumentHeaderTemplateDto headerDto) {
        DivisionType divisionType = convertToDivisionType(headerDto.getDivisionType());
        return mapper.mapToResponseDocumentHeaderDto(
                Objects.requireNonNullElseGet(
                        repository.findByDocumentTypeIdAndDivisionType(headerDto.getDocumentTypeId(), divisionType)
                     , () -> repository.save(create(headerDto, divisionType))));
    }

    @Override
    public ResponseDocumentHeaderTemplateDto update(DocumentHeaderTemplateDto headerDto) {
        if (repository.existsById(headerDto.getId())) {
            return mapper.mapToResponseDocumentHeaderDto(
                    repository.save(create(headerDto, convertToDivisionType(headerDto.getDivisionType()))));
        }
        throw new NotFoundException(
                String.format("Document header with id=%s not found for update", headerDto.getId()));
    }

    @Override
    public List<ResponseDocumentHeaderTemplateDto> getAll(Long id) {
        return getAllByDocumentTypeId(id).stream()
                              .map(mapper::mapToResponseDocumentHeaderDto)
                              .toList();
    }

    @Override
    public Set<DocumentHeaderTemplate> getAllByDocumentTypeId(Long documentTypeId) {
        Set<DocumentHeaderTemplate> documentHeaders = repository.findAllByDocumentTypeId(documentTypeId);
        if (documentHeaders.isEmpty()) {
            throw new NotFoundException(
                    String.format("Document header with document type id=%s not found", documentTypeId));
        }
        return documentHeaders;
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Document header with id=%s not found for delete", id));
    }

    private DivisionType convertToDivisionType(String divisionType) {
        return DivisionType.from(divisionType)
                .orElseThrow(() -> new BadRequestException(
                        String.format("Unknown data format divisionType=%s", divisionType))
                );
    }

    private DocumentHeaderTemplate create(DocumentHeaderTemplateDto headerDto, DivisionType divisionType) {
        DivisionDto divisionDto = getDivision(divisionType, headerDto.getDivisionId());
        String division = divisionDto.getShortName();
        String address = "";
        String certificate = "";
        String contacts = "";
        if (headerDto.getSpecifyFullName()) {
            division = divisionDto.getFullName();
            if (divisionDto.getNumber() != null) {
                division = String.join(" ", division, "№", String.valueOf(divisionDto.getNumber()));
            }
        }
        if (headerDto.getSpecifyAddress() && divisionDto.getAddress() != null) {
            address = stringBuilderService.buildAddress(divisionDto.getAddress());
        }
        if (headerDto.getSpecifyLicense()) {
            List<LaboratoryCertificateDto> certificates = laboratoryCertificateService.getAll()
                                                       .stream()
                                                       .filter(c -> c.getDivisionId().equals(headerDto.getDivisionId()))
                                                       .toList();
            if (!certificates.isEmpty()) {
                certificate = stringBuilderService.buildCertificate(laboratoryCertificateService.getAll());
            }
        }
        if (headerDto.getSpecifyContacts() && headerDto.getEmployeeId() != null && headerDto.getEmployeeId() > 0) {
            EmployeeDto employee = client.getAllEmployees(headerDto.getEmployeeId(), headerDto.getDivisionType())
                    .stream()
                    .collect(Collectors.toMap(EmployeeDto::getId, e -> e))
                    .get(headerDto.getEmployeeId());
            if (employee != null) {
                contacts = stringBuilderService.buildEmployeeContacts(employee);
            }
        }
        return mapper.mapToDocumentHeader(headerDto, divisionType, division, address, certificate, contacts);
    }

    private DivisionDto getDivision(DivisionType divisionType, Long divisionId) {
        switch (divisionType) {
            case ORGANIZATION -> {
                return mapper.mapFromOrganization(client.getOrganization(divisionId));
            }
            case BRANCH -> {
                return mapper.mapFromBranch(client.getBranch(divisionId));
            }
            case DEPARTMENT -> {
                return mapper.mapFromDepartment(client.getDepartment(divisionId));
            }
            case HEAT_SUPPLE_AREA -> {
                return mapper.mapFromHeatSupplyAreaDto(client.getHeatSupplyArea(divisionId));
            }
            case EXPLOITATION_REGION -> {
                return mapper.mapFromExploitationRegionDto(client.getExploitationRegion(divisionId));
            }
            default ->
                    throw new BadRequestException(
                            String.format(String.format("Unknown divisionType=%s", divisionType))
                    );
        }
    }
}