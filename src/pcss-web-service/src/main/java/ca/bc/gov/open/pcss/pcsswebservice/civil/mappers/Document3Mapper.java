package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.Document3;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedCivilFileContentDocumentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( uses = {ObjectFactory.class, DocumentSupportMapper.class })
public interface Document3Mapper {



    @Mapping(target = "civilDocumentId", source = "civildocumentid")
    @Mapping(target = "fileSeqNo", source = "fileseqnumber")
    @Mapping(target = "documentTypeCd", source = "documenttypecd")
    @Mapping(target = "filedDt", source = "fileddt")
    @Mapping(target = "commentTxt", source = "commenttxt")
    @Mapping(target = "concludedYn", source = "concludedyn")
    @Mapping(target = "lastAppearanceId", source = "lastappearanceid")
    @Mapping(target = "lastAppearanceDt", source = "lastappearnacedt")
    @Mapping(target = "lastAppearanceTm", source = "lastappearnacedt")
    @Mapping(target = "documentSupport", source = "civilFileContentDocumentSupportDataList")
    Document3 toDocument3(ExtendedCivilFileContentDocumentData extendedCivilFileContentDocumentData);


}
