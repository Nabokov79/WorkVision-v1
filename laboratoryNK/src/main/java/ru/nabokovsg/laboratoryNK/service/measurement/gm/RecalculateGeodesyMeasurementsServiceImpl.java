package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecalculateGeodesyMeasurementsServiceImpl implements RecalculateGeodesyMeasurementsService {

    @Override
    public List<GeodesicMeasurement> recalculateByTransition(List<GeodesicMeasurement> geodesicMeasurements) {
        int delta = 0;
        Map<Integer, GeodesicMeasurement> measurements = geodesicMeasurements.stream()
                .collect(Collectors.toMap(GeodesicMeasurement::getSequentialNumber, g -> g));
        for (int i = 1;  i <= measurements.size(); i++) {
            GeodesicMeasurement measurement = measurements.get(i);
            measurements.put(measurement.getSequentialNumber(), getRecalculateMeasurement(measurement, delta));
            if (measurement.getTransitionValue() != null) {
                delta = getDelta(measurement.getControlPointValue(), measurement.getTransitionValue());
            }
        }
        return new ArrayList<>(measurements.values());
    }

    private GeodesicMeasurement getRecalculateMeasurement(GeodesicMeasurement measurement, int delta) {
        if (measurement.getReferencePointValue() != null) {
            measurement.setReferencePointValue(
                    getSumMeasurementAndDelta(measurement.getReferencePointValue(), delta)
            );
        }
        measurement.setControlPointValue(getSumMeasurementAndDelta(measurement.getControlPointValue(), delta));
        return measurement;
    }

    private Integer getDelta(int measurementValue, int transitionValue) {
        return measurementValue - transitionValue;
    }

    private Integer getSumMeasurementAndDelta(int measurementValue, int delta) {
        return measurementValue + delta;
    }
}