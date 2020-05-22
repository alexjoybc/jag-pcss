package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.*;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedCivilFileContentData;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@DisplayName("CivilService: gvilSearchFileAppearanceDocument test suite")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CivilGetFileDetailCivilTest {


    public static final String CIVILDOCUMENTID = "Civildocumentid";
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
    private static final String COMMENTTXT = "Commenttxt";
    private static final String CONCLUDEDYN = "Concludedyn";
    private static final String DOCUMENTTYPECD = "Documenttypecd";
    private static final String FILEDDT = "Fileddt";
    private static final String FILESEQNUMBER = "Fileseqnumber";
    private static final String LASTAPPEARANCEID = "Lastappearanceid";
    private static final String LASTAPPEARNACEDT = "Lastappearnacedt";
    private static final String LASTAPPEARANCETM = "Lastappearancetm";
    private static final String ACTCD = "Actcd";
    private static final String ACTDSC = "Actdsc";
    private CivilServiceImpl sut;

    @Mock
    private PcssCivilApi pcssCivilApiMock;

    @BeforeEach
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(pcssCivilApiMock.civilFileContentGet(TestHelpers.testCase("Exception 1"))).thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new CivilServiceImpl(pcssCivilApiMock);
    }

    @DisplayName("Case 1: When api return 1 document, should return first")
    @Test
    public void withOneDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.testCase("1"), 1, 1, true, 1, true, 1, true);
    }

    @DisplayName("Case 2: When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.testCase("2"), 100, 100, true, 100, true, 100, true);
    }

    @DisplayName("Case 3: When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentErrorPartyShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.testCase("3"), 100, 100, false, 1, false, 1, false);
    }

    @DisplayName("Case 4: When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentErrorDocumentSupportPartyShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.testCase("4"), 100, 100, false, 1, true, 1, false);
    }

    @DisplayName("Case Exception 1: When api error")
    @Test
    public void withApiExceptionShouldReturnResult() throws ApiException {

        ExtendedCivilFileContentData actual = sut.getFileDetailCivil(TestHelpers.testCase("Exception 1"));

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
                             boolean isPartySuccess,
                             int documentGetCount,
                             boolean isDocumentSuccess,
                             int documentSupportGetCount,
                             boolean isDocumentSupportSuccess) throws ApiException {


        Mockito.when(pcssCivilApiMock.civilFileContentGet(Mockito.eq(testCase)))
                .thenReturn(getFakeResponse(documentCount));

        Mockito.when(pcssCivilApiMock.civilFileContentPartyGet(Mockito.eq(testCase)))
                .thenReturn(getFakePartyResponse(partyCount, isPartySuccess));
        
        Mockito.when(pcssCivilApiMock.civilFileContentDocumentGet(Mockito.eq(testCase)))
                .thenReturn(getFakeDocument(documentGetCount, isDocumentSuccess));

        Mockito.when(pcssCivilApiMock.civilFileContentDocumentSupportGet(Mockito.eq(CIVILDOCUMENTID)))
                .thenReturn(getFakeDocumentSupport(documentSupportGetCount, isDocumentSupportSuccess));

        ExtendedCivilFileContentData actual = sut.getFileDetailCivil(testCase);

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
        
        if(isDocumentSuccess) {

            Assertions.assertEquals(documentGetCount, actual.getExtendedCivilFileContentDocumentDataList().size());

            actual.getExtendedCivilFileContentDocumentDataList().stream().forEach(item -> {

                Assertions.assertEquals(CIVILDOCUMENTID, item.getCivildocumentid());
                Assertions.assertEquals(COMMENTTXT, item.getCommenttxt());
                Assertions.assertEquals(CONCLUDEDYN, item.getConcludedyn());
                Assertions.assertEquals(DOCUMENTTYPECD, item.getDocumenttypecd());
                Assertions.assertEquals(FILEDDT, item.getFileddt());
                Assertions.assertEquals(FILESEQNUMBER, item.getFileseqnumber());
                Assertions.assertEquals(LASTAPPEARANCEID, item.getLastappearanceid());
                Assertions.assertEquals(LASTAPPEARANCETM, item.getLastappearancetm());
                Assertions.assertEquals(LASTAPPEARNACEDT, item.getLastappearnacedt());

                if(isDocumentSupportSuccess) {
                    Assertions.assertEquals(documentGetCount, item.getCivilFileContentDocumentSupportDataList().size());

                    item.getCivilFileContentDocumentSupportDataList().stream().forEach(documentSupport -> {

                        Assertions.assertEquals(ACTCD, documentSupport.getActcd());
                        Assertions.assertEquals(ACTDSC, documentSupport.getActdsc());

                    });


                } else {
                    Assertions.assertEquals(0, item.getCivilFileContentDocumentSupportDataList().size());
                }

            });

        } else {
            Assertions.assertEquals(0, actual.getExtendedCivilFileContentDocumentDataList().size());
        }

    }

    private CivilFileContentDocumentSupportResponse getFakeDocumentSupport(int documentSupportGetCount, boolean isDocumentSupportSuccess) {

        CivilFileContentDocumentSupportResponse response = new CivilFileContentDocumentSupportResponse();
        response.setResponseCd(isDocumentSupportSuccess ? TestHelpers.SUCCESS_RESPONSE_CD :
                TestHelpers.DEFAULT_ERROR_RESPONSE_CD);
        response.setResponseMsg(isDocumentSupportSuccess ? TestHelpers.SUCCESS_RESPONSE_MSG :
                TestHelpers.DEFAULT_ERROR_RESPONSE_MSG);

        for(int i =0; i < documentSupportGetCount; i++) {


            CivilFileContentDocumentSupportData item = new CivilFileContentDocumentSupportData();

            item.setActcd(ACTCD);
            item.setActdsc(ACTDSC);

            response.addDataItem(item);

        }

        return response;

    }

    private CivilFileContentDocumentResponse getFakeDocument(int documentGetCount, boolean isDocumentSuccess) {

        CivilFileContentDocumentResponse response = new CivilFileContentDocumentResponse();
        response.setResponseCd(isDocumentSuccess ? TestHelpers.SUCCESS_RESPONSE_CD :
                TestHelpers.DEFAULT_ERROR_RESPONSE_CD);
        response.setResponseMsg(isDocumentSuccess ? TestHelpers.SUCCESS_RESPONSE_MSG :
                TestHelpers.DEFAULT_ERROR_RESPONSE_MSG);

        for (int i = 0; i < documentGetCount; i++) {


            CivilFileContentDocumentData item = new CivilFileContentDocumentData();
            
            item.setCivildocumentid(CIVILDOCUMENTID);
            item.setCommenttxt(COMMENTTXT);
            item.setConcludedyn(CONCLUDEDYN);
            item.setDocumenttypecd(DOCUMENTTYPECD);
            item.setFileddt(FILEDDT);
            item.setFileseqnumber(FILESEQNUMBER);
            item.setLastappearanceid(LASTAPPEARANCEID);
            item.setLastappearancetm(LASTAPPEARANCETM);
            item.setLastappearnacedt(LASTAPPEARNACEDT);
            response.addDataItem(item);

        }

        return response;
        
        
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
