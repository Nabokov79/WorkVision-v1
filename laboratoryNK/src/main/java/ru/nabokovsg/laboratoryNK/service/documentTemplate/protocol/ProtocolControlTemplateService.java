package ru.nabokovsg.laboratoryNK.service.documentTemplate.protocol;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ResponseProtocolControlTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.protocolControl.ShortResponseProtocolControlTemplateDto;

import java.util.List;

public interface ProtocolControlTemplateService {

    ShortResponseProtocolControlTemplateDto save(ProtocolControlTemplateDto protocolDto);

    ShortResponseProtocolControlTemplateDto update(ProtocolControlTemplateDto protocolDto);

    ResponseProtocolControlTemplateDto get(Long id);

    List<ShortResponseProtocolControlTemplateDto> getAll();

    void delete(Long id);
}