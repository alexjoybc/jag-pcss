package ca.bc.gov.open.pcss.ords.pcss.client.civil.models;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentDocumentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentDocumentSupportData;

import java.util.ArrayList;
import java.util.List;

public class ExtendedCivilFileContentDocumentData extends CivilFileContentDocumentData {

    private List<CivilFileContentDocumentSupportData> civilFileContentDocumentSupportDataList = new ArrayList<>();

    public List<CivilFileContentDocumentSupportData> getCivilFileContentDocumentSupportDataList() {
        return civilFileContentDocumentSupportDataList;
    }

    public void addAll(List<CivilFileContentDocumentSupportData> civilFileContentDocumentSupportDataList) {
        this.civilFileContentDocumentSupportDataList.addAll(civilFileContentDocumentSupportDataList);
    }
}
