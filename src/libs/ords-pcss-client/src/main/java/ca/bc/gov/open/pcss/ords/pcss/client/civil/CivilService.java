package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.FileContentResponse;

public interface CivilService {

    AppearanceDocumentResponse getCivilSearchFileAppearanceDocument(String appearanceId);

    SearchFileAppearanceResourcesResponse getAppearanceCivilResource(String appearanceId);

    FileContentResponse getFileDetailCivil(String physicalFileId);

}
