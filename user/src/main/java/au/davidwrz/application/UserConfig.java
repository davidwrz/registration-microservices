package au.davidwrz.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class UserConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
