package ssuSoftware.hearHear.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(title = "Hear here API 명세서",
        version = "v1", description = "스웨거를 이용한 aPI관리")
)
public class SwaggerConfig {



    @Bean
    public GroupedOpenApi hearHere(){
        String[] path = {"/v1/**"};

        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/v1/**")
                .build();
    }

}
