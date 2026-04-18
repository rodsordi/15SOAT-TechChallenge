package br.com.fiap.commons.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Configuration
public class SwaggerConfig {

    @Value("${info.app.title}")
    private String appTitle;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.app.description}")
    private String appDescription;

    @Autowired
    private Environment env;

    @Bean
    public OpenAPI openAPI(Info info, Collection<Map.Entry<String, Example>> examples) {
        var openApi = new OpenAPI()
                .info(info);
        examples.forEach(example -> openApi.getComponents()
                .addExamples(example.getKey(), example.getValue()));
        return openApi;
    }

    @Bean
    public Info info() {
        return new Info()
                .title(appTitle)
                .version(appVersion)
                .description(appDescription);
    }
}
