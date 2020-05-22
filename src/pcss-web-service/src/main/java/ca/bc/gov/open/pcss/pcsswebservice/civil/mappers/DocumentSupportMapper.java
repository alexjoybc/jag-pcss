package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.DocumentSupport;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentDocumentSupportData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

@Mapper(uses = {ObjectFactory.class})
public interface DocumentSupportMapper {


    @Mapping(target = "actCd", source = "actcd")
    @Mapping(target = "actDsc", source = "actdsc")
    DocumentSupport toDocumentSupport(CivilFileContentDocumentSupportData civilFileContentDocumentSupportData);

}
