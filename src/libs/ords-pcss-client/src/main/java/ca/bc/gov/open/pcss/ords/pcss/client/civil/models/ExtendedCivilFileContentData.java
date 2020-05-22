package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExtendedCivilFileContentData extends CivilFileContentData implements ResponseBase {

    private String responseMsg;

    private BigDecimal responseCd;

    private List<ExtendedPartyData> partyData = new ArrayList<>();

    private List<ExtendedCivilFileContentDocumentData> extendedCivilFileContentDocumentDataList = new ArrayList<>();

    public ExtendedCivilFileContentData() {
    }

    private ExtendedCivilFileContentData(BigDecimal responseCd, String responseMsg) {
        this.responseMsg = responseMsg;
        this.responseCd = responseCd;
    }

    @Override
    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public BigDecimal getResponseCd() {
        return responseCd;
    }

    public void setResponseCd(BigDecimal responseCd) {
        this.responseCd = responseCd;
    }

    public List<ExtendedPartyData> getPartyData() {
        return partyData;
    }

    public List<ExtendedCivilFileContentDocumentData> getExtendedCivilFileContentDocumentDataList() {
        return extendedCivilFileContentDocumentDataList;
    }

    public void addAll(List<ExtendedPartyData> partyData) {
        this.partyData.addAll(partyData);
    }

    public void addAllExtendedCivilFileContentDocumentData(List<ExtendedCivilFileContentDocumentData> extendedCivilFileContentDocumentDataList) {
        this.extendedCivilFileContentDocumentDataList.addAll(extendedCivilFileContentDocumentDataList);
    }

    public static ExtendedCivilFileContentData ErrorResponse(String message) {
        return new ExtendedCivilFileContentData(BigDecimal.valueOf(Integer.valueOf(Keys.DEFAULT_ERROR_RESPONSE_CD)), message);
    }



}
