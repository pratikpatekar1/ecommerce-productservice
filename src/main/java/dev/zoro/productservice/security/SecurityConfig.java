package dev.zoro.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //Testing hasAuthority
//                        .requestMatchers("/products/{id}").hasAuthority("ADMIN") //TODO: change this to use proper authorization
                        .anyRequest().permitAll()
                )
//                .oauth2ResourceServer((resourceServer) -> resourceServer
//                        .jwt(
//                            jwtConfigurer -> {
//                                jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
//                            }
//                            ))
                .cors().disable()
                .csrf().disable();
//                // Form login handles the redirect to the login page from the
//                // authorization server filter chain
////                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
