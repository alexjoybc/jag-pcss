package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.*;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers.*;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentResponse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

public class CivilServiceImpl implements CivilService {

    public static final BigDecimal SUCCESS_RESPONSE_CODE = BigDecimal.valueOf(0);
    private final PcssCivilApi pcssCivilApi;

    public CivilServiceImpl(PcssCivilApi pcssCivilApi) {
        this.pcssCivilApi = pcssCivilApi;
    }

    public AppearanceDocumentResponse getCivilSearchFileAppearanceDocument(String appearanceId) {

        try {

            AppearanceDocumentResponse result =
                    AppearanceDocumentResponseMapper.INSTANCE.toAppearanceDocumentResponse(this
                    .pcssCivilApi
                    .civilSearchFileAppearanceDocumentGet(appearanceId));

            result.getData().stream().forEach(document -> {
                if (StringUtils.isNotBlank(document.getDocumentid())) {
                    SearchFileAppearanceIssueResponse documentIsssues = getFileAppearanceDocumentIssue(appearanceId,
                            document.getDocumentid());

                    if (documentIsssues.getResponseCd().equals(SUCCESS_RESPONSE_CODE)) {
                        document.addAll(documentIsssues.getData());
                    }
                }
            });

            return result;

        } catch (ApiException e) {
            return AppearanceDocumentResponseMapper.INSTANCE.toAppearanceDocumentResponse(e);
        }
    }

    private SearchFileAppearanceIssueResponse getFileAppearanceDocumentIssue(String appearanceId, String documentId) {

        try {
            return this.pcssCivilApi.civilSearchFileAppearanceIssueGet(appearanceId, documentId);
        } catch (ApiException e) {
            return SearchFileAppearanceIssueResponseMapper.INSTANCE.toSearchFileAppearanceIssueResponse(e);
        }

    }


    public SearchFileAppearanceResourcesResponse getAppearanceCivilResource(String appearanceId) {

        try {
            return this.pcssCivilApi.civilSearchFileAppearanceResourcesGet(appearanceId);
        } catch (ApiException e) {
            return SearchFileAppearanceResourcesResponseMapper.INSTANCE.toSearchFileAppearanceResourcesResponse(e);
        }

    }


    public FileContentResponse getFileDetailCivil(String physicalFileId) {

        try {

            CivilFileContentResponse result = this.pcssCivilApi.civilFileContentGet(physicalFileId);

            if (result.getData() == null) return FileContentResponse.ErrorResponse("CivilFileContentData not found");

            Optional<CivilFileContentData> firstItem = result.getData().stream().findFirst();

            if (!firstItem.isPresent()) return FileContentResponse.ErrorResponse("CivilFileContentData not found");

            FileContentResponse response = FileContentResponseMapper.INSTANCE.toFileContentResponse(result,
                    firstItem.get());

            CivilFileContentPartyResponse civilFileContentPartyResponse = getCivilFileContentParty(physicalFileId);

            if(civilFileContentPartyResponse != null && civilFileContentPartyResponse.getResponseCd().equals(SUCCESS_RESPONSE_CODE))
                response.addAll(civilFileContentPartyResponse.getData());

            return response;


        } catch (ApiException e) {
            return FileContentResponseMapper.INSTANCE.toFileContentResponse(e);
        }

    }

    private CivilFileContentPartyResponse getCivilFileContentParty(String physicalFileId) {
        try {
            return this.pcssCivilApi.civilFileContentPartyGet(physicalFileId);
        } catch (ApiException e) {
            return CivilFileContentPartyResponseMapper.INSTANCE.toCivilFileContentPartyResponse(e);
        }
    }

}
