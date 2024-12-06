/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 12/6/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins("http://localhost:63342") // Specify allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Specify allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true) // Allow sending cookies/credentials
                        .maxAge(3600); // Cache preflight response for 1 hour
            }
        };
    }
}
