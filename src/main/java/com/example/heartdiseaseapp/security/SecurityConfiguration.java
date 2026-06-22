package com.example.heartdiseaseapp.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration // indique que c'est une classe de configuration spring
@EnableWebSecurity // pour activer la securité web dans l'application
@EnableMethodSecurity(prePostEnabled = true) // pour activer les annotations de sécurité au niveau (controller) des
                                             // méthodes comme @PreAuthorize
public class SecurityConfiguration {
    // ---------------------------Swagger--------------------------------------------
    // pour qu'il ne soit pas sécurisé et qu'on puisse y acceder sans auth

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/webjars/**");
    }

    @Bean
    // c'est la configuration de base pour travailer sur une app sans sécurite pour
    // apres quand termine le full stack on la securise avec keycloak
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // --------------------Security Filter-----------------------------------
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))// pour activer le cors
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// pour preciser
                                                                                                   // qu'on a une
                                                                                                   // authentification
                                                                                                   // de type stateless
                .csrf(csrf -> csrf.disable())// csrf il faut le désactiver pour une authentification stateless
                .headers(h -> h.frameOptions(fo -> fo.disable()))
                .authorizeHttpRequests(ar -> ar.requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs",
                        "/webjars/**").permitAll() // Swagger sans auth
                        .anyRequest().authenticated()) // le reste est sécurisé

                // il faut pas declarer .authorizehttprequetes deux fois car une va ecraser
                // l'autre et on va perdre la configuration de la premiere

                .oauth2ResourceServer(o2 -> o2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtAuthConverter()))) // pour
                                                                                                                   // dire
                                                                                                                   // que
                                                                                                                   // notre
                                                                                                                   // app
                                                                                                                   // est
                                                                                                                   // un
                                                                                                                   // resource
                                                                                                                   // server
                                                                                                                   // et
                                                                                                                   // qu'on
                                                                                                                   // va
                                                                                                                   // utiliser
                                                                                                                   // le
                                                                                                                   // jwt
                                                                                                                   // pour
                                                                                                                   // l'authentification
                                                                                                                   // et
                                                                                                                   // on
                                                                                                                   // va
                                                                                                                   // utiliser
                                                                                                                   // notre
                                                                                                                   // converter
                                                                                                                   // pour
                                                                                                                   // extraire
                                                                                                                   // les
                                                                                                                   // roles
                                                                                                                   // du
                                                                                                                   // jwt

                .build();
    }

    // ---------------------------Cors(Cross-Origin Resource Sharing)--------------------------------------------
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://host.docker.internal:4200"));// pour autoriser les requetes venant de l'angular qui tourne sur
                                                     // le port 4200 et aussi pour autoriser les requetes venant de
                                                     // l'angular qui tourne dans le docker
         configuration.setAllowedMethods(List.of(
        "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
    ));
         configuration.setAllowedHeaders(List.of(
        "Authorization",
        "Content-Type",
        "Accept",
        "Origin",
        "X-Requested-With"
    ));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
