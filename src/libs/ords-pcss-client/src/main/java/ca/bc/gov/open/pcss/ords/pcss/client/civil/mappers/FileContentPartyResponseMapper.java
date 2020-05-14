package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentPartyResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentPartyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileContentPartyResponseMapper {

    FileContentPartyResponseMapper INSTANCE = Mappers.getMapper(FileContentPartyResponseMapper.class);

    FileContentPartyResponse toFileContentPartyResponse(CivilFileContentPartyResponse civilFileContentPartyResponse);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    FileContentPartyResponse toFileContentPartyResponse(ApiException e);

}
