package ru.nabokovsg.gateway.dto.laboratoryNK.common.measuringTool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Данные поиска измерительнго инструменте(приборе)")
public class SearchParameters {

    private List<Long> ids;
    private String toll;
    private String model;
    private String workNumber;
    private LocalDate manufacturing;
    private LocalDate exploitation;
    private String manufacturer;
    private String organization;
    private String controlType;
    private Long employeeId;
}