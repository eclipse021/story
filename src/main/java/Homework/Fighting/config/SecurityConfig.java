package Homework.Fighting.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // @Configuration @Bean으로 등록해서 사용한다
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다
@AllArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    @Bean //비밀번호 암호화를 담당하는 메소드
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean //시큐리티 대부분 설정을 담당하는 메소드
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //메서드 참조 방식
        //http.csrf(AbstractHttpConfigurer::disable);
        //람다 활용 방식
        http.csrf((csrfConfig) ->  csrfConfig.disable());
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션을 안 쓰는 stateless 만들기
        http.httpBasic((basic)->basic.disable()); //bearer방식을 쓰기 위해 basic disable 하기
        //http.addFilter(corsFilter);
        http.apply(new MyCustomDsl()); // 최신 버전은 apply가 사라져 http.with(new MyCustomDsl(), Customizer.withDefaults());로 대체해야함

        http.authorizeRequests()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>{

        @Override
        public void configure(HttpSecurity http) throws Exception {
            //super.configure(builder);

            http.addFilter(corsConfig.corsFilter());

        }
    }
}
