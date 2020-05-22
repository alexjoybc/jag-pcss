package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentDocumentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.CivilFileContentDocumentSupportData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.ExtendedCivilFileContentDocumentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExtendedCivilFileContentDocumentDataMapper {

    ExtendedCivilFileContentDocumentDataMapper INSTANCE = Mappers.getMapper(ExtendedCivilFileContentDocumentDataMapper.class);

    @Mapping(target="civilFileContentDocumentSupportDataList", source="civilFileContentDocumentSupportData" )
    ExtendedCivilFileContentDocumentData toExtendedCivilFileContentDocumentData(
            CivilFileContentDocumentData civilFileContentDocumentData,
            List<CivilFileContentDocumentSupportData> civilFileContentDocumentSupportData);


}
