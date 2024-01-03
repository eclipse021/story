package Homework.Fighting.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // @Configuration @Bean으로 등록해서 사용한다
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록된다
@AllArgsConstructor
public class SecurityConfig {

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


        // authorizeRequests는 deprecated로 권장 되지 않으므로 authorizeHttpRequests 사용하기
        http.authorizeRequests()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN");
                //.anyRequest().permitAll();



        //오류 발생
        /*http.authorizeHttpRequests((authorize) ->
                authorize
                    .requestMatchers("/hello/**").authenticated()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                    .anyRequest().permitAll()
        );
*/
        //user, admin과 같이 권한이 없는 페이지에 들어갈 때 login
       /* http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/login")

        );*/
        return http.build();
    }


}
