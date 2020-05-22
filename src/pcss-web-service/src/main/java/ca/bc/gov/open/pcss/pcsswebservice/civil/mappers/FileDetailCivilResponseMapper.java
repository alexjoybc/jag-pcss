package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetFileDetailCivilResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedCivilFileContentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, Party3Mapper.class, Document3Mapper.class } )
public interface FileDetailCivilResponseMapper {

    FileDetailCivilResponseMapper INSTANCE = Mappers.getMapper( FileDetailCivilResponseMapper.class );

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "physicalFileId", source = "physicalfileid")
    @Mapping(target = "fileNumberTxt", source = "filenumbertxt")
    @Mapping(target = "homeLocationAgenId", source = "homelocationagentid")
    @Mapping(target = "courtLevelCd", source = "courtlevelcd")
    @Mapping(target = "courtClassCd", source = "courtclasscd")
    @Mapping(target = "socTxt", source = "soctxt")
    @Mapping(target = "leftRoleDsc", source = "leftroledsc")
    @Mapping(target = "rightRoleDsc", source = "rightroledsc")
    @Mapping(target = "trialRemarkTxt", source = "trialremark")
    @Mapping(target = "commentToJudgeTxt", source = "commenttojudgetxt")
    @Mapping(target = "party", source = "partyData")
    @Mapping(target = "document", source = "extendedCivilFileContentDocumentDataList")
    GetFileDetailCivilResponse toGetFileDetailCivilResponse(ExtendedCivilFileContentData extendedCivilFileContentData);

}
