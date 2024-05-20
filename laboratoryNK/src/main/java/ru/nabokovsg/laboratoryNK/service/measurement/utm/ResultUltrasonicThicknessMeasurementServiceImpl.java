package ru.nabokovsg.laboratoryNK.service.measurement.utm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.measurement.utm.ResultUltrasonicThicknessMeasurementServiceMapper;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.StandardSize;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableThickness;
import ru.nabokovsg.laboratoryNK.repository.measurement.utm.ResultUltrasonicThicknessMeasurementRepository;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.StandardSizeService;
import ru.nabokovsg.laboratoryNK.service.measurement.vms.DefectMeasurementService;
import ru.nabokovsg.laboratoryNK.service.norms.AcceptableThicknessService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ResultUltrasonicThicknessMeasurementServiceImpl
                                        implements ResultUltrasonicThicknessMeasurementService {

    private final ResultUltrasonicThicknessMeasurementRepository repository;
    private final ResultUltrasonicThicknessMeasurementServiceMapper mapper;
    private final AcceptableThicknessService acceptableThicknessService;
    private final DefectMeasurementService defectMeasurementService;
    private final StandardSizeService standardSizeService;

    @Override
    public void save(UltrasonicThicknessMeasurement measurement) {
        AcceptableThickness acceptableThickness = acceptableThicknessService.getAcceptableThickness(measurement);
        ResultUltrasonicThicknessMeasurement calculatingMeasurement =
                                                     mapper.mapToCalculatingUltrasonicThicknessMeasurement(measurement);
        getAcceptableMin(calculatingMeasurement, acceptableThickness);
        setResidualThickness(calculatingMeasurement
                          , defectMeasurementService.getMaxCorrosionValueByPredicate(measurement));
        setAcceptableMeasurement(calculatingMeasurement, acceptableThickness);
        correctResidualThickness(calculatingMeasurement, acceptableThickness);
        repository.save(calculatingMeasurement);
    }

    @Override
    public Set<ResultUltrasonicThicknessMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId) {
        Set<ResultUltrasonicThicknessMeasurement> measurements =
                repository.findAllBySurveyJournalIdAndEquipmentId(surveyJournalId, equipmentId);
        if (measurements.isEmpty()) {
            throw new NotFoundException(
                    String.format("UltrasonicThicknessMeasurement with surveyJournalId=%s" +
                                                                     " and surveyJournalId=%s not found"
                                                                     , surveyJournalId, equipmentId));
                                        }
        return measurements;
    }

    private void setResidualThickness(ResultUltrasonicThicknessMeasurement measurement, Double maxCorrosion) {
        if (maxCorrosion != null) {
            measurement.setResidualThickness(measurement.getMinMeasurementValue() - maxCorrosion);
        } else {
            measurement.setResidualThickness(measurement.getMinMeasurementValue());
        }
    }

    private void getAcceptableMin(ResultUltrasonicThicknessMeasurement measurement
                                , AcceptableThickness acceptableThickness) {
        StandardSize standardSize = standardSizeService.getByPredicate(measurement);
        if (acceptableThickness.getAcceptablePercent() != null) {
            measurement.setMinAcceptableValue(standardSize.getDesignThickness()
                                                                    * (acceptableThickness.getAcceptablePercent()/100));
        } else {
            measurement.setMinAcceptableValue(acceptableThickness.getMinThickness());
        }
    }

    private void correctResidualThickness(ResultUltrasonicThicknessMeasurement measurement
                                        , AcceptableThickness acceptableThickness) {
        if (measurement.getReachedInvalidValue()) {
            measurement.setResidualThickness(acceptableThickness.getMinThickness());
        }
    }

    private void setAcceptableMeasurement(ResultUltrasonicThicknessMeasurement measurement
                                        , AcceptableThickness acceptableThickness) {
        measurement.setNoStandard(acceptableThickness.getMinThickness() == null);
        if (measurement.getNoStandard()) {
            // Допустимое значение
            measurement.setAcceptableValue(
                    measurement.getResidualThickness() >= sum(acceptableThickness.getMinThickness()
                                                            , acceptableThickness.getMeasurementError())
            );
            // Недопустимое значение(брак)
            measurement.setInvalidValue(sum(measurement.getResidualThickness()
                                          , acceptableThickness.getMeasurementError())
                                        < acceptableThickness.getMinThickness());
            // Приближается к недопустимому значению(брак)
            measurement.setApproachingInvalidValue(measurement.getResidualThickness()
                    > acceptableThickness.getMinThickness()
                    && sum(acceptableThickness.getMinThickness()
                         , acceptableThickness.getMeasurementError())
                    > measurement.getResidualThickness()
            );
            // Достигнуто недопустимое значение
            measurement.setReachedInvalidValue(sum(measurement.getResidualThickness()
                                             , acceptableThickness.getMeasurementError())
                                            == acceptableThickness.getMinThickness());
        }
    }

    private double sum(double first, double second) {
        return first + second;
    }
}