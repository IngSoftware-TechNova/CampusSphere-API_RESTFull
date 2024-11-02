package com.technova.campussphereapi.config;

import com.technova.campussphereapi.security.JWTConfigurer;
import com.technova.campussphereapi.security.JWTFilter;
import com.technova.campussphereapi.security.JwtAuthenticationEntryPoint;
import com.technova.campussphereapi.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Importante para anotaciones @PreAuthoriza
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Todo: Permite solicitudes CORS desde otros dominios
                .csrf(AbstractHttpConfigurer::disable) // Todo: Desactiva la proteccion CSRF, ya que en APIs REST no se usa (se autentica con tokens, no con cookies)

                .authorizeHttpRequests(authorize -> authorize
                        // Todo: Permitir acceso público a las rutas de login, registro y endpoints públicos como Swagger UI
                        .requestMatchers(antMatcher("/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/auth/register/student")).permitAll()
                        .requestMatchers(antMatcher("/events/recent")).permitAll()
                        .requestMatchers(antMatcher("/mail/**")).permitAll()
                        .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html/**", "/swagger-ui/**", "/webjars/**").permitAll()
                        // Todo: Cualquier otra solicitud requiere autenticación (JWT u otra autenticación configurada)
                        .anyRequest().authenticated()
                )

                // Todo: Permite la autenticación básica (para testing con Postman, por ejemplo)
                //.httpBasic(Customizer.withDefaults())
                // Todo: Desactiva el formulario de inicio de sesión predeterminado, ya que se usará JWT
                .formLogin(AbstractHttpConfigurer::disable)
                // Todo: Configura el manejo de excepciones para autenticación. Usa JwtAuthenticationEntryPoint para manejar errores 401 (no autorizado)
                .exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                // Todo: Configura la política de sesiones como "sin estado" (stateless), ya que JWT maneja la autenticación, no las sesiones de servidor
                .sessionManagement(h->h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Todo: Agrega la configuración para JWT en el filtro antes de los filtros predeterminados de Spring Security
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

        // Todo: Añadir el JWTFilter antes del filtro de autenticación de nombre de usuario/contraseña.
        //  Esto permite que el JWTFilter valide el token antes de la autenticación
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Todo: Proporciona el AuthenticationManager que gestionará la autenticación basada en los detalles de usuario y contraseña
        return authenticationConfiguration.getAuthenticationManager();
    }
}
