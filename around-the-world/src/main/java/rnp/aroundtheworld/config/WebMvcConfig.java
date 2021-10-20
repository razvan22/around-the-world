package rnp.aroundtheworld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/login")
               .allowedMethods("POST")
               .allowedOrigins("http://localhost:3000")
               .exposedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With", "requestId", "Correlation-Id");
    }


}
//   .allowedMethods("POST")
//           .allowedOrigins("http://localhost:3000")
//           .allowedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With", "requestId", "Correlation-Id")
//           .exposedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Authorization", "X-Requested-With", "requestId", "Correlation-Id")
//           .maxAge(MAX_AGE_SECS);