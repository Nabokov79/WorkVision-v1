package ru.nabokovsg.gateway.dto.laboratoryNK.common.surveyJournal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные для добавления информации об выполняемой работе")
public class NewSurveyJournalDto {

    @Schema(description = "Дата обследования")
    private LocalDate date;
    @Schema(description = "Индентификатор филиала организации")
    @NotNull(message = "branch id should not be blank")
    @Positive(message = "branch id must be positive")
    private Long branchId;
    @Schema(description = "Индентификатор района теплоснабжения")
    @NotNull(message = "heat supply area id should not be blank")
    @Positive(message = "heat supply area id must be positive")
    private Long heatSupplyAreaId;
    @Schema(description = "Индентификатор эксплуатационного участка")
    @NotNull(message = "exploitation region id should not be blank")
    @Positive(message = "exploitation region id must be positive")
    private Long exploitationRegionId;
    @Schema(description = "Место проведения работ")
    @NotNull(message = "address id should not be blank")
    @Positive(message = "address id must be positive")
    private Long addressId;
    @Schema(description = "Индентификатор диагностируемого оборудования")
    @NotNull(message = "equipment id should not be blank")
    @Positive(message = "equipment id must be positive")
    private Long equipmentId;
    @Schema(description = "Тип выполняемой работы")
    @NotBlank(message = "work type should not be blank")
    private String workType;
    @Schema(description = "Основание проведения работы(адресная программа, заявка, распоряжение руководителя)")
    @NotBlank(message = "task source should not be blank")
    private String taskSource;
    @Schema(description = "Комментарий к задаче")
    private String comment;
    @Schema(description = "Индентификатор руководителя работ")
    @NotNull(message = "laboratory employee chef id should not be blank")
    @Positive(message = "laboratory employee chef id must be positive")
    private Long laboratoryEmployeeId;
    @Schema(description = "Индентификаторы сотрудников лаборатории назначенных для выполнения задачи")
    @NotNull(message = "laboratory employee ids should not be null")
    @NotEmpty(message = "laboratory employee ids should not be empty")
    private List<Long> laboratoryEmployeesIds;
    @Schema(description = "Индентификатор типа документа диагностики")
    @NotNull(message = "diagnostic document type id should not be null")
    @NotEmpty(message = "diagnostic document type id should not be empty")
    private Long diagnosticDocumentTypeId;
    @Schema(description = "необходимо приложить чертеж")
    @NotNull(message = "drawing should not be null")
    private boolean drawing;
    @Schema(description = "Оборудование с теплоносителем или без него")
    @NotNull(message = "full should not be null")
    private Boolean full;
}