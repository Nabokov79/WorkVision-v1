package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.DeviationYear;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculationGeodesicMeasurementServiceImpl implements CalculationGeodesicMeasurementService {

    @Override
    public Integer getMinMeasurement(List<Integer> calculatedHeights) {
        Optional<Integer> min = calculatedHeights.stream()
                .filter(Objects::nonNull)
                .min(Integer::compareTo);
        if (min.isEmpty()) {
            return 0;
        } else {
            return min.get();
        }
    }

    @Override
    public Integer getDeviation(Integer min, Integer calculatedHeight) {
        return min - calculatedHeight;
    }

    @Override
    public Integer getPrecipitation(Integer newDeviation, List<DeviationYear> deviationYears) {
        if (deviationYears == null) {
            return 0;
        }
        Map<Integer, Integer> deviationYeasDb = deviationYears.stream()
                .collect(Collectors.toMap(DeviationYear::getYear
                        , DeviationYear::getDeviation));
        return newDeviation - deviationYeasDb.get(deviationYeasDb.keySet()
                .stream()
                .mapToInt(d -> d)
                .max()
                .orElseThrow(NoSuchElementException::new));
    }
}