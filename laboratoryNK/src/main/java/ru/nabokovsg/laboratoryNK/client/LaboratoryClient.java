package ru.nabokovsg.laboratoryNK.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.client.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratoryClient {

    private final CompanyClient companyClient;
    private static final String DELIMITER = "/";
    private static final String API_PREFIX_EMPLOYEE = "/employee";
    private static final String API_PREFIX_ORGANIZATION = "/organization";
    private static final String API_PREFIX_BRANCH = "/branch";
    private static final String API_PREFIX_DEPARTMENT = "/department";
    private static final String API_PREFIX_HEAT_SUPPLE_AREA = "/heat/supply/area";
    private static final String API_PREFIX_EXPLOITATION_REGION = "/exploitation";

    public List<EmployeeDto> getAllEmployees(Long id, String divisionType) {
        return companyClient.getAllEmployees(String.join(DELIMITER, API_PREFIX_EMPLOYEE,
                "all", String.valueOf(id)), "divisionType", divisionType);
    }

    public OrganizationDto getOrganization(Long id) {
        return companyClient.getOrganization(String.join(DELIMITER, API_PREFIX_ORGANIZATION, String.valueOf(id)));
    }

    public BranchDto getBranch(Long id) {
        return companyClient.getBranch(String.join(DELIMITER, API_PREFIX_BRANCH, String.valueOf(id)));
    }

    public HeatSupplyAreaDto getHeatSupplyArea(Long id) {
        return companyClient.getHeatSupplyArea(String.join(DELIMITER, API_PREFIX_HEAT_SUPPLE_AREA, String.valueOf(id)));
    }
    public DepartmentDto getDepartment(Long id) {
        return companyClient.getDepartment(String.join(DELIMITER, API_PREFIX_DEPARTMENT, String.valueOf(id)));
    }
    public ExploitationRegionDto getExploitationRegion(Long id) {
        return companyClient.getExploitationRegion(
                String.join(DELIMITER, API_PREFIX_EXPLOITATION_REGION, String.valueOf(id)));
    }
}