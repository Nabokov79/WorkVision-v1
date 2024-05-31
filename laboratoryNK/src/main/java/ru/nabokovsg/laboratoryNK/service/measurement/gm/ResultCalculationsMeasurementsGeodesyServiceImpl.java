package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.GeodesicMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.AcceptableDeviationsGeodesy;
import ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed.EquipmentDiagnosedService;
import ru.nabokovsg.laboratoryNK.service.norms.AcceptableDeviationsGeodesyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultCalculationsMeasurementsGeodesyServiceImpl implements ResultCalculationsMeasurementsGeodesyService {

    private final RecalculateGeodesyMeasurementsService recalculateGeodesyMeasurementsService;
    private final AcceptableDeviationsGeodesyService acceptableDeviationsGeodesyService;
    private final EquipmentDiagnosedService equipmentDiagnosedService;
    private final ControlPointMeasurementService controlPointMeasurementService;
    private final ReferencePointMeasurementService referencePointMeasurementService;
    private final PointDifferenceService pointDifferenceService;

    @Override
    public void save(Long equipmentId, List<GeodesicMeasurement> measurements) {
        AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                = acceptableDeviationsGeodesyService.getByDataOfEquipmentForCalculations(
                        equipmentDiagnosedService.getById(equipmentId));
        int geodesyLocations = equipmentDiagnosedService.getGeodesyLocationsById(equipmentId);
        if (measurements.size() == geodesyLocations && geodesyLocations > 0) {
            List<GeodesicMeasurement> geodesicMeasurements
                                        = recalculateGeodesyMeasurementsService.recalculateByTransition(measurements);
            referencePointMeasurementService.save(acceptableDeviationsGeodesy, geodesicMeasurements);
           pointDifferenceService.save(acceptableDeviationsGeodesy
                                    , controlPointMeasurementService.save(geodesicMeasurements));
        }
    }

    public void update(Long equipmentId, List<GeodesicMeasurement> measurements) {
        EquipmentDiagnosed equipment = equipmentDiagnosedService.getById(equipmentId);
        AcceptableDeviationsGeodesy acceptableDeviationsGeodesy
                = acceptableDeviationsGeodesyService.getByDataOfEquipmentForCalculations(equipment);
        List<GeodesicMeasurement> geodesicMeasurements
                = recalculateGeodesyMeasurementsService.recalculateByTransition(measurements);
        referencePointMeasurementService.update(acceptableDeviationsGeodesy, geodesicMeasurements);
        pointDifferenceService.update(acceptableDeviationsGeodesy
                                  , controlPointMeasurementService.update(geodesicMeasurements));
    }
}