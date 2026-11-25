
package pe.edu.upc.demovi.securities;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Origen permitido: aplicación Angular
        configuration.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        );

        // Cabeceras HTTP permitidas
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Cabeceras que Angular puede leer en la respuesta
        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        // No se utilizan cookies de autenticación
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        // Aplicar CORS a todos los endpoints
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/roles/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/usuarios/me").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/cantidades").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").hasAnyRole("ADMIN", "DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/apoderados/progreso").hasRole("APODERADO")

                        .requestMatchers(HttpMethod.GET, "/api/ejercicios", "/api/ejercicios/materias")
                        .hasAnyRole("ADMIN", "DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/api/ejercicios/*")
                        .hasAnyRole("ADMIN", "DOCENTE")

                        .requestMatchers(HttpMethod.GET, "/api/evaluaciones/cantidades")
                        .hasAnyRole("ADMIN", "DOCENTE")
                        .requestMatchers(HttpMethod.GET, "/api/evaluaciones/estudiante/**")
                        .hasAnyRole("ADMIN", "DOCENTE", "ESTUDIANTE")
                        .requestMatchers(HttpMethod.GET, "/api/evaluaciones", "/api/evaluaciones/*")
                        .hasAnyRole("ADMIN", "DOCENTE")

                        .anyRequest().authenticated()
                )

                // habilitar después
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        new CustomJwtAuthenticationConverter()
                                )
                        )
                );

        return http.build();
    }
}
