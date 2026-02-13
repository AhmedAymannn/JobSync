package Ahmed.com.JobSync.security;
import Ahmed.com.JobSync.common.Enums.Role;
import Ahmed.com.JobSync.common.exception.JwtCreationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.token.lifetime}")
    private long TOKEN_LIFETIME ;
    private  Key secretKey ;
    @Value("${jwt.token.secret}")
    private String SECRET_KEY;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //1 Token Generation
    public String createToken (Long userId , Role role){
        try {
            Date now = new Date();
            Date expiry = new Date(now.getTime() + TOKEN_LIFETIME);
            return Jwts.builder()
                    .setSubject(userId.toString())
                    .claim("role", role.name())
                    .setIssuedAt(now)
                    .setExpiration(expiry)
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new JwtCreationException("Failed To Creating Jwt Token " , e);
        }
    }
    // 2 : Extract All Claims From The Token
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();                    // Changed from getBody()

    }
    // 3 : validate the token if it is valid
    public boolean tokenIsValid (String token){
            Jwts.parser()
                    .verifyWith((SecretKey) secretKey)
                    .build()
                    .parseSignedClaims(token);
            // If it reaches here, the signature is valid and it's not expired
            return true;
    }
    // 4: Exctracting User Id
    public Long extractUserId (String token){
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) secretKey) // Changed from setSigningKey
                .build()
                .parseSignedClaims(token)           // Changed from parseClaimsJws
                .getPayload();
        return Long.parseLong(claims.getSubject());
    }
}
