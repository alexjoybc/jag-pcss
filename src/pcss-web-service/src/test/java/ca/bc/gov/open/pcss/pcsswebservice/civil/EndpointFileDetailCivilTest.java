package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetFileDetailCivilRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetFileDetailCivilResponse2;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.CourtClassType;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.CourtLevelType;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.LeftRightType;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.CivilService;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedCivilFileContentData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedPartyData;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndpointFileDetailCivilTest {

    private static final String COMMENTTOJUDGETXT = "Commenttojudgetxt";
    private static final String COURTCLASSCD = "A";
    private static final String COURTLEVELCD = "P";
    private static final String FILENUMBERTXT = "Filenumbertxt";
    private static final String HOMELOCATIONAGENTID = "Homelocationagentid";
    private static final String LEFTROLEDSC = "Leftroledsc";
    private static final String PHYSICALFILEID = "Physicalfileid";
    private static final String RIGHTROLEDSC = "Rightroledsc";
    private static final String SOCTXT = "Soctxt";
    private static final String TRIALREMARK = "Trialremark";
    private static final String PARTYROLETYPE = "Partyroletype";
    private static final String PARTYID = "1";
    private static final String ORGNM = "Orgnm";
    private static final String GIVENNM = "Givennm";
    private static final String LASTNM = "Lastnm";
    private static final String LEFTRIGHTCD = "L";
    private static final String SELFREPRESENTEDYN = "Selfrepresentedyn";


    public PcssCivilEndpoint sut;

    @Mock
    public PcssCivilApi pcssCivilApiMock;

    @Mock
    public CivilService civilServiceMock;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        Mockito
                .when(civilServiceMock.getFileDetailCivil(Mockito.eq(TestHelpers.CASE_1)))
                .thenReturn(getFakeFileDetailCivilResponse());

        Mockito
                .when(civilServiceMock.getFileDetailCivil(Mockito.eq(TestHelpers.CASE_2)))
                .thenReturn(getFakeErrorFileDetailCivilResponse());

        sut = new PcssCivilEndpoint(pcssCivilApiMock, civilServiceMock);

    }

    @DisplayName(TestHelpers.CASE_1 + ": When the PCSS api returns a single result.")
    @Test
    public void withResultShouldReturnResult() {

        GetFileDetailCivilRequest request = new GetFileDetailCivilRequest();

        ca.bc.gov.open.pcss.civil.GetFileDetailCivilRequest request2 = new ca.bc.gov.open.pcss.civil.GetFileDetailCivilRequest();

        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestPartId(TestHelpers.REQUEST_PART_ID);

        request2.setPhysicalFileId(TestHelpers.CASE_1);

        request.setGetFileDetailCivilRequest(request2);

        GetFileDetailCivilResponse2 actual =  sut.getFileDetailCivil(request);

        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_CD, actual.getGetFileDetailCivilResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_MSG, actual.getGetFileDetailCivilResponse().getResponseMessageTxt());
        Assertions.assertEquals(COMMENTTOJUDGETXT, actual.getGetFileDetailCivilResponse().getCommentToJudgeTxt());
        Assertions.assertEquals(CourtClassType.A, actual.getGetFileDetailCivilResponse().getCourtClassCd());
        Assertions.assertEquals(CourtLevelType.P, actual.getGetFileDetailCivilResponse().getCourtLevelCd());
        Assertions.assertEquals(FILENUMBERTXT, actual.getGetFileDetailCivilResponse().getFileNumberTxt());
        Assertions.assertEquals(HOMELOCATIONAGENTID, actual.getGetFileDetailCivilResponse().getHomeLocationAgenId());
        Assertions.assertEquals(LEFTROLEDSC, actual.getGetFileDetailCivilResponse().getLeftRoleDsc());
        Assertions.assertEquals(PHYSICALFILEID, actual.getGetFileDetailCivilResponse().getPhysicalFileId());
        Assertions.assertEquals(RIGHTROLEDSC, actual.getGetFileDetailCivilResponse().getRightRoleDsc());
        Assertions.assertEquals(SOCTXT, actual.getGetFileDetailCivilResponse().getSocTxt());
        Assertions.assertEquals(TRIALREMARK, actual.getGetFileDetailCivilResponse().getTrialRemarkTxt());
        
        actual.getGetFileDetailCivilResponse().getParty().forEach(party -> {

            Assertions.assertEquals(PARTYROLETYPE, party.getRoleTypeCd());
            Assertions.assertEquals(PARTYID, party.getPartyId());
            Assertions.assertEquals(ORGNM, party.getOrgNm());
            Assertions.assertEquals(GIVENNM, party.getGivenNm());
            Assertions.assertEquals(LASTNM, party.getLastNm());
            Assertions.assertEquals(LeftRightType.L, party.getLeftRightCd());
            Assertions.assertEquals(SELFREPRESENTEDYN, party.getSelfRepresentedYN());


        });

    }

    @DisplayName(TestHelpers.CASE_2 + ": When the PCSS api returns an error")
    @Test
    public void withErrorShouldReturnResult() {

        GetFileDetailCivilRequest request = new GetFileDetailCivilRequest();

        ca.bc.gov.open.pcss.civil.GetFileDetailCivilRequest request2 = new ca.bc.gov.open.pcss.civil.GetFileDetailCivilRequest();

        request2.setRequestPartId(TestHelpers.REQUEST_PART_ID);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);

        request2.setPhysicalFileId(TestHelpers.CASE_2);

        request.setGetFileDetailCivilRequest(request2);

        GetFileDetailCivilResponse2 actual =  sut.getFileDetailCivil(request);

        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_CD, actual.getGetFileDetailCivilResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_MSG, actual.getGetFileDetailCivilResponse().getResponseMessageTxt());

    }


    private ExtendedCivilFileContentData getFakeFileDetailCivilResponse() {

        ExtendedCivilFileContentData response = new ExtendedCivilFileContentData();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.SUCCESS_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);

        response.setCommenttojudgetxt(COMMENTTOJUDGETXT);
        response.setCourtclasscd(COURTCLASSCD);
        response.setCourtlevelcd(COURTLEVELCD);
        response.setFilenumbertxt(FILENUMBERTXT);
        response.setHomelocationagentid(HOMELOCATIONAGENTID);
        response.setLeftroledsc(LEFTROLEDSC);
        response.setPhysicalfileid(PHYSICALFILEID);
        response.setRightroledsc(RIGHTROLEDSC);
        response.setSoctxt(SOCTXT);
        response.setTrialremark(TRIALREMARK);


        List<ExtendedPartyData> partyDataList = new ArrayList<>();
        ExtendedPartyData partyData = new ExtendedPartyData();
        partyData.setPartyroletype(PARTYROLETYPE);
        partyData.setPartyid(BigDecimal.valueOf(Integer.valueOf(PARTYID)));
        partyData.setOrgnm(ORGNM);
        partyData.setGivennm(GIVENNM);
        partyData.setLastnm(LASTNM);
        partyData.setLeftrightcd(LEFTRIGHTCD);
        partyData.setSelfrepresentedyn(SELFREPRESENTEDYN);
        partyDataList.add(partyData);

        response.addAll(partyDataList);

        return response;

    }

    private ExtendedCivilFileContentData getFakeErrorFileDetailCivilResponse() {

        ExtendedCivilFileContentData response = new ExtendedCivilFileContentData();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.ERROR_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.ERROR_RESPONSE_MSG);

        return response;

    }



}
