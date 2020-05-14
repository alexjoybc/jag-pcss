package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentCounselData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedPartyData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExtendedPartyDataMapper {

    ExtendedPartyDataMapper INSTANCE = Mappers.getMapper(ExtendedPartyDataMapper.class);

    @Mapping(target="civilFileContentCounselDataList", source="civilFileContentCounselData" )
    ExtendedPartyData toExtendedPartyData(PartyData partyData, List<CivilFileContentCounselData> civilFileContentCounselData);

}
