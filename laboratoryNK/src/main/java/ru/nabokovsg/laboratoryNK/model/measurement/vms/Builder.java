package ru.nabokovsg.laboratoryNK.model.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;

import java.util.List;

public class Builder {

    private final Defect defect;
    private final ElementRepair elementRepair;
    private final DefectMeasurement defectMeasurement;
    private final CompletedRepairElement completedRepair;
    private final List<ParameterMeasurementDto> parameterMeasurementsDto;

    public Builder(ParameterMeasurementBuilder builder) {
        this.defect = builder.defect;
        this.defectMeasurement = builder.defectMeasurement;
        this.elementRepair = builder.elementRepair;
        this.completedRepair = builder.completedRepair;
        this.parameterMeasurementsDto = builder.parameterMeasurementsDto;
    }

    public Defect getDefect() {
        return defect;
    }

    public ElementRepair getElementRepair() {
        return elementRepair;
    }

    public DefectMeasurement getDefectMeasurement() {
        return defectMeasurement;
    }

    public CompletedRepairElement getCompletedRepair() {
        return completedRepair;
    }

    public List<ParameterMeasurementDto> getParameterMeasurementsDto() {
        return parameterMeasurementsDto;
    }

    public static class ParameterMeasurementBuilder {
        private Defect defect;
        private DefectMeasurement defectMeasurement;
        private ElementRepair elementRepair;
        private CompletedRepairElement completedRepair;
        private List<ParameterMeasurementDto> parameterMeasurementsDto;

        public ParameterMeasurementBuilder defect(Defect defect) {
            this.defect = defect;
            return this;
        }

        public ParameterMeasurementBuilder defectMeasurement(DefectMeasurement defectMeasurement) {
            this.defectMeasurement = defectMeasurement;
            return this;
        }

        public ParameterMeasurementBuilder elementRepair(ElementRepair elementRepair) {
            this.elementRepair = elementRepair;
            return this;
        }

        public ParameterMeasurementBuilder completedRepair(CompletedRepairElement completedRepair) {
            this.completedRepair = completedRepair;
            return this;
        }

        public ParameterMeasurementBuilder parameterMeasurements(List<ParameterMeasurementDto> parameterMeasurementsDto) {
            this.parameterMeasurementsDto = parameterMeasurementsDto;
            return this;
        }

        public Builder build() {
            return new Builder(this);
        }
    }
}
