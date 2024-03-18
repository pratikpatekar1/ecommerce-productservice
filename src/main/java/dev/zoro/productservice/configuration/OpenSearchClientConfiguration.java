package dev.zoro.productservice.configuration;

import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.time.Duration;

@Configuration
public class OpenSearchClientConfiguration extends AbstractOpenSearchConfiguration {
    @Value("${opensearch.host}")
    private String opensearchHost;
    @Value("${opensearch.username}")
    private String opensearchUsername;
    @Value("${opensearch.password}")
    private String opensearchPassword;
    @Override
    @Bean
    public RestHighLevelClient opensearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(opensearchHost)
                .usingSsl()
                .withConnectTimeout(Duration.ofSeconds(10))
                .withBasicAuth(opensearchUsername, opensearchPassword)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
//    @Bean
//    public RestClient restClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(opensearchHost)
//                .usingSsl()
//                .withBasicAuth(opensearchUsername, opensearchPassword)
//                .build();
//        return RestClients.create(clientConfiguration).lowLevelRest();
//    }
//    @Bean
//    @Primary
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return elasticsearchOperations();
//    }

}
