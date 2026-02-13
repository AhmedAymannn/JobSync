package Ahmed.com.JobSync.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    UserDetailsServiceImp userDetailsServiceImp ;
    JwtAuthFilter jwtAuthFilter ;

    public WebSecurityConfig(UserDetailsServiceImp userDetailsServiceImp, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disable the csrf
                .csrf(customizer -> customizer.disable()) // Disable for APIs
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/auth/**").permitAll() // Let people register
                        .anyRequest().authenticated()                // Lock everything else
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Add our Custom Filter BEFORE the standard one
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);// // Allow Postman

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
