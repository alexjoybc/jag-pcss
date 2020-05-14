package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileContentPartyResponse implements ResponseBase {

    private String responseMsg;

    private BigDecimal responseCd;

    private List<ExtendedPartyData> extendedPartyDataList = new ArrayList<>();

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

    public List<ExtendedPartyData> getExtendedPartyDataList() {
        return extendedPartyDataList;
    }

    public void addAll(List<ExtendedPartyData> extendedPartyDataList) {
        this.extendedPartyDataList.addAll(extendedPartyDataList);
    }
}
