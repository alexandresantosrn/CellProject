package br.com.cellprojectback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8081").allowedMethods("GET", "POST", "PUT",
				"DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");		
		
	}
//	
//	 @Bean
//     public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        // Permitir origens específicas (substitua * pela sua origem)
//        config.addAllowedOrigin("http://localhost:8081");
//
//        // Permitir métodos específicos (GET, POST, etc.)
//        config.addAllowedMethod("*");
//
//        // Permitir headers específicos
//        config.addAllowedHeader("Access-Control-Allow-Headers");
//        config.addAllowedHeader("Access-Control-Allow-Origin");
//        // Adicione outros headers que você deseja permitir
//
//        // Permitir cookies e credenciais (se necessário)
//        config.setAllowCredentials(true);
//
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(source);
//    }
}
