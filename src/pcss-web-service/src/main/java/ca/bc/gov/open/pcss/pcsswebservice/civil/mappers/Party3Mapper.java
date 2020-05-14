package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.civil.Party3;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedPartyData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface Party3Mapper {


    @Mapping(target = "partyId", source = "partyid")
    @Mapping(target = "lastNm", source = "lastnm")
    @Mapping(target = "givenNm", source = "givennm")
    @Mapping(target = "orgNm", source = "orgnm")
    @Mapping(target = "roleTypeCd", source = "partyroletype")
    @Mapping(target = "leftRightCd", source = "leftrightcd")
    @Mapping(target = "selfRepresentedYN", source = "selfrepresentedyn")
    Party3 toParty3(ExtendedPartyData partyData);

}
