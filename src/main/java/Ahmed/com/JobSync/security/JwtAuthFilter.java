package Ahmed.com.JobSync.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
            // 1 : using the search
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // token
            final String token = authHeader.substring(7);
            // 2 : Validation and Identification
            //  Check if the token is valid. If it is, extract the ID.
        try {
            if (jwtService.tokenIsValid(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                Claims claims = jwtService.extractAllClaims(token);
                Long userId = Long.parseLong(claims.getSubject());
                // This is the line that usually crashes if you don't use .name() in the Service
                String roleName = claims.get("role", String.class);
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, null, List.of(authority));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (ExpiredJwtException e) {
            // Log the error so you can see it in the console!
            System.out.println("Security Filter Error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token has expired\", \"code\": \"TOKEN_EXPIRED\"}");
            response.getWriter().write("""
                    PLease Login Again
                    """);
            response.getWriter().write("""
                   http://localhost:8080/api/v1/auth/login
                    """);
            return;
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{ \"message\": \"Access Denied\" }");
            return;
        }
        filterChain.doFilter(request, response);
        }
}
