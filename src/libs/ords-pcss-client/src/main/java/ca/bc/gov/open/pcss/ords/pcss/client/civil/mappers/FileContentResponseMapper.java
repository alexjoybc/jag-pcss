package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileContentResponseMapper {

    FileContentResponseMapper INSTANCE = Mappers.getMapper(FileContentResponseMapper.class);

    @Mapping(target = "responseCd", source = "civilFileContentResponse.responseCd")
    @Mapping(target = "responseMsg", source = "civilFileContentResponse.responseMsg")
    @Mapping(target = "physicalfileid", source="civilFileContentData.physicalfileid")
    @Mapping(target = "filenumbertxt", source="civilFileContentData.filenumbertxt")
    @Mapping(target = "homelocationagentid", source="civilFileContentData.homelocationagentid")
    @Mapping(target = "courtlevelcd", source="civilFileContentData.courtlevelcd")
    @Mapping(target = "courtclasscd", source="civilFileContentData.courtclasscd")
    @Mapping(target = "soctxt", source="civilFileContentData.soctxt")
    @Mapping(target = "leftroledsc", source="civilFileContentData.leftroledsc")
    @Mapping(target = "rightroledsc", source="civilFileContentData.rightroledsc")
    @Mapping(target = "trialremark", source="civilFileContentData.trialremark")
    @Mapping(target = "commenttojudgetxt", source="civilFileContentData.commenttojudgetxt")
    FileContentResponse toFileContentResponse(CivilFileContentResponse civilFileContentResponse, CivilFileContentData civilFileContentData);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    FileContentResponse toFileContentResponse(ApiException apiException);

}
