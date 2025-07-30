package com.hzokbe.eigakan.configuration.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.stream.Collectors;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Value("${rsa.key.public}")
    private RSAPublicKey rsaPublicKey;

    @Value("${rsa.key.private}")
    private RSAPrivateKey rsaPrivateKey;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
    }

    @Bean
    public JwtEncoder getJwtEncoder() {
        var jwk = new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build();

        var jwkSet = new ImmutableJWKSet<SecurityContext>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwkSet);
    }

    @Bean
    public JwtDecoder getJwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity security) throws Exception {
        return
                security
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(
                                r -> r
                                        .requestMatchers(HttpMethod.POST, "/sign-up")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.POST, "/sign-in")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET, "/movies/**")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.GET, "/people/**")
                                        .authenticated()
                                        .anyRequest()
                                        .denyAll()
                        )
                        .httpBasic(Customizer.withDefaults())
                        .oauth2ResourceServer(configurer -> configurer.jwt(converter -> converter.jwtAuthenticationConverter(getJwtAuthenticationConverter())))
                        .build();
    }

    public JwtAuthenticationConverter getJwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                jwt -> jwt.getClaimAsStringList("roles")
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.toUpperCase()))
                        .collect(Collectors.toList())
        );

        return converter;
    }
}
