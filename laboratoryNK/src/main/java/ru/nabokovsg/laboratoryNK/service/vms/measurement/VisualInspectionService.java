package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.visualInspection.VisualInspectionDto;


public interface VisualInspectionService {

    ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto);

    ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto);
}