package com.example.login_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((request) -> request
                .requestMatchers("/myAccount", "/myLoans", "/myBalance", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact").permitAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean // Spring framework 에서 Bean으로 관리해준다. -> 해당 방식은 메모리에 사용자 저장. 단지 테스트용, 비권장 방식이다.
    public InMemoryUserDetailsManager userDetailsService() {
        /*
         * user details를 생성할 때 withDetaultPasswordEncoder() 메서드를 사용한다.
         * */

        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("12345"))
            .authorities("admin")
            .build();
        UserDetails user = User.withUsername("user")
            .password(
                passwordEncoder().encode("12345 ")) // 비밀번호를 암호화하지 않고 그냥 String으로 박으면 자격증명 실패가 뜨넹
            .authorities("read")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    //
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
