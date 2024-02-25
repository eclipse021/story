package Homework.Fighting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    CorsFilter corsFilter(){
        System.out.println("cors 필터를 탔습니다.");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자바스크립트 등에서 json 등의 데이터를 요청할 때 제대로 받고 서버에서 응답가능하게 하겠다.
        config.addAllowedOrigin("*"); // 모든 ip의 응답을 허용하겠다.
        config.addAllowedHeader("*"); // 모든 header의 응답을 허용하겠다.
        config.addAllowedMethod("*"); // 모든 post, get, patch 등의 요청을 허용하겠다.

        source.registerCorsConfiguration("*",config);
        return new CorsFilter(source);

    }




}
