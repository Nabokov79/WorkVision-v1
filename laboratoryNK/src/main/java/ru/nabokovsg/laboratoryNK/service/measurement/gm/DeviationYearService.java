package ru.nabokovsg.laboratoryNK.service.measurement.gm;

import ru.nabokovsg.laboratoryNK.model.measurement.gm.ReferencePoint;

import java.util.List;

public interface DeviationYearService {

    void save(List<ReferencePoint> points);

    void update(List<ReferencePoint> points);
}