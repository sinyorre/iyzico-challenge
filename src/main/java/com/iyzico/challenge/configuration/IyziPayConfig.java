package com.iyzico.challenge.configuration;

import com.iyzipay.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IyziPayConfig {

    @Value("${apiKey}")
    String apiKey;

    @Value("${secretKey}")
    String secretKey;

    @Value("${baseUrl}")
    String baseUrl;

    @Bean
    public Options createOption() {
        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);
        return options;
    }

}
