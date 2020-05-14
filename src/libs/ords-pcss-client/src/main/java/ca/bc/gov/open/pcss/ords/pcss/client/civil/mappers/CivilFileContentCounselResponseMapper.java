package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentCounselResponse;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CivilFileContentCounselResponseMapper {

    CivilFileContentCounselResponseMapper INSTANCE = Mappers.getMapper(CivilFileContentCounselResponseMapper.class);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    CivilFileContentCounselResponse toCivilFileContentCounselResponse(ApiException e);

}
