package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;


public interface VisualInspectionService {

    ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto);

    ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto);
}