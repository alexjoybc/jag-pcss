package ca.bc.gov.open.pcss.ords.pcss.client;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PcssOrdsProperties.class)
public class AutoConfiguration {

    private final PcssOrdsProperties pcssOrdsProperties;

    public AutoConfiguration(PcssOrdsProperties pcssOrdsProperties) {
        this.pcssOrdsProperties = pcssOrdsProperties;
    }

    @Bean(name = "pcssApiClient")
    public ApiClient apiClient() {
        ApiClient apiClient = new ApiClient();

        apiClient.setBasePath(pcssOrdsProperties.getBasePath());

        if(StringUtils.isNotBlank(pcssOrdsProperties.getUsername()))
            apiClient.setUsername(pcssOrdsProperties.getUsername());

        if(StringUtils.isNotBlank(pcssOrdsProperties.getPassword()))
            apiClient.setPassword(pcssOrdsProperties.getPassword());

        return apiClient;
    }

    @Bean
    public PcssApi otssoaApi(@Qualifier("pcssApiClient") ApiClient apiClient) {
        return new PcssApi(apiClient);
    }

}
