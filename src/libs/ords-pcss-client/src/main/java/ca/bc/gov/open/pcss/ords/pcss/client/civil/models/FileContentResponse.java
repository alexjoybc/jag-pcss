package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileContentResponse extends CivilFileContentData implements ResponseBase {

    private String responseMsg;

    private BigDecimal responseCd;

    private List<PartyData> partyData = new ArrayList<>();

    public FileContentResponse() {
    }

    private FileContentResponse(BigDecimal responseCd, String responseMsg) {
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

    public List<PartyData> getPartyData() {
        return partyData;
    }

    public static FileContentResponse ErrorResponse(String message) {
        return new FileContentResponse(BigDecimal.valueOf(Integer.valueOf(Keys.DEFAULT_ERROR_RESPONSE_CD)), message);
    }

    public void addAll(List<PartyData> partyData) {
        this.partyData.addAll(partyData);
    }

}
