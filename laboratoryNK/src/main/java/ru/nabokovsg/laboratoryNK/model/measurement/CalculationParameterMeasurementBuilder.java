package ru.nabokovsg.laboratoryNK.model.measurement;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.norms.CalculationType;

import java.util.List;
import java.util.Set;

public class CalculationParameterMeasurementBuilder {

    private final DefectMeasurement defect;
    private final CompletedRepairElement repair;
    private final CalculationType typeCalculation;
    private final Set<MeasuredParameter> measuredParameters;
    private final Set<CalculationParameterMeasurement> parameterMeasurements;
    private final List<ParameterMeasurementDto> parameterMeasurementsDto;

    public CalculationParameterMeasurementBuilder(Builder builder) {
        this.defect = builder.defect;
        this.repair = builder.repair;
        this.typeCalculation = builder.typeCalculation;
        this.measuredParameters = builder.measuredParameters;
        this.parameterMeasurements = builder.parameterMeasurements;
        this.parameterMeasurementsDto = builder.parameterMeasurementsDto;
    }

    public DefectMeasurement getDefect() {
        return defect;
    }

    public CompletedRepairElement getRepair() {
        return repair;
    }

    public CalculationType getTypeCalculation() {
        return typeCalculation;
    }

    public Set<MeasuredParameter> getMeasuredParameters() {
        return measuredParameters;
    }

    public Set<CalculationParameterMeasurement> getParameterMeasurements() {
        return parameterMeasurements;
    }

    public List<ParameterMeasurementDto> getParameterMeasurementsDto() {
        return parameterMeasurementsDto;
    }

    public static class Builder {

        private DefectMeasurement defect;
        private CompletedRepairElement repair;
        private CalculationType typeCalculation;
        private Set<MeasuredParameter> measuredParameters;
        private Set<CalculationParameterMeasurement> parameterMeasurements;
        private List<ParameterMeasurementDto> parameterMeasurementsDto;

        public Builder defect(DefectMeasurement defect) {
            this.defect = defect;
            return this;
        }

        public Builder repair(CompletedRepairElement repair) {
            this.repair = repair;
            return this;
        }

        public Builder typeCalculation(CalculationType typeCalculation) {
            this.typeCalculation = typeCalculation;
            return this;
        }

        public Builder measuredParameters(Set<MeasuredParameter> measuredParameters) {
            this.measuredParameters = measuredParameters;
            return this;
        }

        public Builder parameterMeasurements(Set<CalculationParameterMeasurement> parameterMeasurements) {
            this.parameterMeasurements = parameterMeasurements;
            return this;
        }

        public Builder parameterMeasurementsDto(List<ParameterMeasurementDto> parameterMeasurementsDto) {
            this.parameterMeasurementsDto = parameterMeasurementsDto;
            return this;
        }

        public CalculationParameterMeasurementBuilder build() {
            return new CalculationParameterMeasurementBuilder(this);
        }
    }
}