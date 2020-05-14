package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentPartyResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@DisplayName("CivilService: gvilSearchFileAppearanceDocument test suite")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CivilGetFileDetailCivilTest {


    private static final String COMMENTTOJUDGETXT = "Commenttojudgetxt";
    private static final String COURTCLASSCD = "Courtclasscd";
    private static final String COURTLEVELCD = "Courtlevelcd";
    private static final String FILENUMBERTXT = "Filenumbertxt";
    private static final String HOMELOCATIONAGENTID = "Homelocationagentid";
    private static final String LEFTROLEDSC = "Leftroledsc";
    private static final String PHYSICALFILEID = "Physicalfileid";
    private static final String RIGHTROLEDSC = "Rightroledsc";
    private static final String SOCTXT = "Soctxt";
    private static final String TRIALREMARK = "Trialremark";
    private static final String PARTYROLETYPE = "Partyroletype";
    private static final BigDecimal PARTYID = BigDecimal.valueOf(1);
    private static final String ORGNM = "Orgnm";
    private static final String GIVENNM = "Givennm";
    private static final String LASTNM = "Lastnm";
    private static final String LEFTRIGHTCD = "Leftrightcd";
    private static final String SELFREPRESENTEDYN = "Selfrepresentedyn";
    private CivilServiceImpl sut;

    @Mock
    private PcssCivilApi pcssCivilApiMock;

    @BeforeEach
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(pcssCivilApiMock.civilFileContentGet(TestHelpers.CASE_4)).thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new CivilServiceImpl(pcssCivilApiMock);
    }

    @DisplayName(TestHelpers.CASE_1 + ": When api return 1 document, should return first")
    @Test
    public void withOneDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_1, 1, 1, true);
    }

    @DisplayName(TestHelpers.CASE_2 + ": When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_2, 100, 100, true);
    }

    @DisplayName(TestHelpers.CASE_3 + ": When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentErrorPartyShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_3, 100, 100, false);
    }

    @DisplayName(TestHelpers.CASE_4 + ": When api error")
    @Test
    public void withApiExceptionShouldReturnResult() throws ApiException {

        FileContentResponse actual = sut.getFileDetailCivil(TestHelpers.CASE_4);

        Assertions.assertEquals(TestHelpers.DEFAULT_ERROR_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(TestHelpers.RESPONSE_BODY, actual.getResponseMsg());
        Assertions.assertEquals(null, actual.getCommenttojudgetxt());
        Assertions.assertEquals(null, actual.getCourtclasscd());
        Assertions.assertEquals(null, actual.getCourtlevelcd());
        Assertions.assertEquals(null, actual.getFilenumbertxt());
        Assertions.assertEquals(null, actual.getHomelocationagentid());
        Assertions.assertEquals(null, actual.getLeftroledsc());
        Assertions.assertEquals(null, actual.getPhysicalfileid());
        Assertions.assertEquals(null, actual.getRightroledsc());
        Assertions.assertEquals(null, actual.getSoctxt());
        Assertions.assertEquals(null, actual.getTrialremark());

    }

    private void testSuccess(String testCase,
                             int documentCount,
                             int partyCount,
                             boolean isPartySuccess) throws ApiException {


        Mockito.when(pcssCivilApiMock.civilFileContentGet(Mockito.eq(testCase)))
                .thenReturn(getFakeResponse(documentCount));

        Mockito.when(pcssCivilApiMock.civilFileContentPartyGet(Mockito.eq(testCase)))
                .thenReturn(getFakePartyResponse(partyCount, isPartySuccess));

        FileContentResponse actual = sut.getFileDetailCivil(testCase);

        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_MSG, actual.getResponseMsg());
        Assertions.assertEquals(COMMENTTOJUDGETXT + "0", actual.getCommenttojudgetxt());
        Assertions.assertEquals(COURTCLASSCD, actual.getCourtclasscd());
        Assertions.assertEquals(COURTLEVELCD, actual.getCourtlevelcd());
        Assertions.assertEquals(FILENUMBERTXT, actual.getFilenumbertxt());
        Assertions.assertEquals(HOMELOCATIONAGENTID, actual.getHomelocationagentid());
        Assertions.assertEquals(LEFTROLEDSC, actual.getLeftroledsc());
        Assertions.assertEquals(PHYSICALFILEID, actual.getPhysicalfileid());
        Assertions.assertEquals(RIGHTROLEDSC, actual.getRightroledsc());
        Assertions.assertEquals(SOCTXT, actual.getSoctxt());
        Assertions.assertEquals(TRIALREMARK, actual.getTrialremark());

        if (isPartySuccess) {

            Assertions.assertEquals(partyCount, actual.getPartyData().size());

            actual.getPartyData().stream().forEach(partyItem -> {

                Assertions.assertEquals(PARTYROLETYPE, partyItem.getPartyroletype());
                Assertions.assertEquals(PARTYID, partyItem.getPartyid());
                Assertions.assertEquals(ORGNM, partyItem.getOrgnm());
                Assertions.assertEquals(GIVENNM, partyItem.getGivennm());
                Assertions.assertEquals(LASTNM, partyItem.getLastnm());
                Assertions.assertEquals(LEFTRIGHTCD, partyItem.getLeftrightcd());
                Assertions.assertEquals(SELFREPRESENTEDYN, partyItem.getSelfrepresentedyn());

            });
        } else {
            Assertions.assertEquals(0, actual.getPartyData().size());
        }

    }

    private CivilFileContentPartyResponse getFakePartyResponse(int partyCount, boolean isPartySuccess) {

        CivilFileContentPartyResponse response = new CivilFileContentPartyResponse();
        response.setResponseCd(isPartySuccess ? TestHelpers.SUCCESS_RESPONSE_CD :
                TestHelpers.DEFAULT_ERROR_RESPONSE_CD);
        response.setResponseMsg(isPartySuccess ? TestHelpers.SUCCESS_RESPONSE_MSG :
                TestHelpers.DEFAULT_ERROR_RESPONSE_MSG);

        for (int i = 0; i < partyCount; i++) {

            PartyData item = new PartyData();

            item.setPartyroletype(PARTYROLETYPE);
            item.setPartyid(PARTYID);
            item.setOrgnm(ORGNM);
            item.setGivennm(GIVENNM);
            item.setLastnm(LASTNM);
            item.setLeftrightcd(LEFTRIGHTCD);
            item.setSelfrepresentedyn(SELFREPRESENTEDYN);

            response.addDataItem(item);

        }

        return response;

    }

    private CivilFileContentResponse getFakeResponse(int documentCount) {


        CivilFileContentResponse result = new CivilFileContentResponse();

        result.setResponseCd(TestHelpers.SUCCESS_RESPONSE_CD);
        result.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);

        for (int i = 0; i < documentCount; i++) {

            CivilFileContentData item = new CivilFileContentData();

            item.setCommenttojudgetxt(COMMENTTOJUDGETXT + i);
            item.setCourtclasscd(COURTCLASSCD);
            item.setCourtlevelcd(COURTLEVELCD);
            item.setFilenumbertxt(FILENUMBERTXT);
            item.setHomelocationagentid(HOMELOCATIONAGENTID);
            item.setLeftroledsc(LEFTROLEDSC);
            item.setPhysicalfileid(PHYSICALFILEID);
            item.setRightroledsc(RIGHTROLEDSC);
            item.setSoctxt(SOCTXT);
            item.setTrialremark(TRIALREMARK);

            result.addDataItem(item);

        }

        return result;

    }

}
