package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentCounselData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyData;

import java.util.ArrayList;
import java.util.List;

public class ExtendedPartyData extends PartyData {

    List<CivilFileContentCounselData> civilFileContentCounselDataList = new ArrayList<>();

    public List<CivilFileContentCounselData> getCivilFileContentCounselDataList() {
        return civilFileContentCounselDataList;
    }

    public void addAll(List<CivilFileContentCounselData> civilFileContentCounselDataList) {
        this.civilFileContentCounselDataList.addAll(civilFileContentCounselDataList);
    }

}
