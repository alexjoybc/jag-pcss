package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@DisplayName("CivilService: gvilSearchFileAppearanceDocument test suite")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CivilGetFileDetailCivilTest {


    public static final String COMMENTTOJUDGETXT = "Commenttojudgetxt";
    public static final String COURTCLASSCD = "Courtclasscd";
    public static final String COURTLEVELCD = "Courtlevelcd";
    public static final String FILENUMBERTXT = "Filenumbertxt";
    public static final String HOMELOCATIONAGENTID = "Homelocationagentid";
    public static final String LEFTROLEDSC = "Leftroledsc";
    public static final String PHYSICALFILEID = "Physicalfileid";
    public static final String RIGHTROLEDSC = "Rightroledsc";
    public static final String SOCTXT = "Soctxt";
    public static final String TRIALREMARK = "Trialremark";
    private CivilServiceImpl sut;

    @Mock
    private PcssCivilApi pcssCivilApiMock;

    @BeforeEach
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(pcssCivilApiMock.civilFileContentGet(TestHelpers.CASE_3)).thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new CivilServiceImpl(pcssCivilApiMock);
    }

    @DisplayName(TestHelpers.CASE_1 + ": When api return 1 document, should return first")
    @Test
    public void withOneDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_1, 1);
    }

    @DisplayName(TestHelpers.CASE_2 + ": When api return 100 document, should return first only")
    @Test
    public void withMultipleDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_2, 100);
    }

    @DisplayName(TestHelpers.CASE_3 + ": When api error")
    @Test
    public void withApiExceptionShouldReturnResult() throws ApiException {

        FileContentResponse actual = sut.getFileDetailCivil(TestHelpers.CASE_3);

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

    private void testSuccess(String testCase, int documentCount) throws ApiException {


        Mockito.when(pcssCivilApiMock.civilFileContentGet(Mockito.eq(testCase)))
                .thenReturn(getFakeResponse(documentCount));

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
