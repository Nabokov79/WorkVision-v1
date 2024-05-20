package ru.nabokovsg.laboratoryNK.mapper.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.ResponseSurveyJournalDto;
import ru.nabokovsg.laboratoryNK.dto.surveyJournal.SurveyJournalDto;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;

@Mapper(componentModel = "spring")
public interface SurveyJournalMapper {

    @Mapping(source = "journalDto.date", target = "date")
    @Mapping(source = "branch", target = "branch")
    @Mapping(source = "heatSupplyArea", target = "heatSupplyArea")
    @Mapping(source = "exploitationRegion", target = "exploitationRegion")
    @Mapping(source = "building", target = "building")
    @Mapping(source = "equipmentId", target = "equipmentId")
    @Mapping(source = "equipmentDiagnosed", target = "equipmentDiagnosed")
    @Mapping(source = "journalDto.workType", target = "workType")
    @Mapping(source = "journalDto.taskSource", target = "taskSource")
    @Mapping(source = "journalDto.comment", target = "comment")
    @Mapping(source = "journalDto.id", target = "id")
    SurveyJournal mapToSurveyJournal(SurveyJournalDto journalDto
                                 , String branch
                                 , String heatSupplyArea
                                 , String exploitationRegion
                                 , String building
                                 , Long equipmentId
                                 , String equipmentDiagnosed);

    ResponseSurveyJournalDto mapToResponseTaskJournalDto(SurveyJournal journal);
}