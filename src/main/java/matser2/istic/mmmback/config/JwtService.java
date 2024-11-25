package matser2.istic.mmmback.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final Key secretKey;
    private static final long JWT_EXPIRATION = 1000 * 60 * 60 * 10;
    private static final long REFRESH_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    /**
     * Constructeur : Génère une clé secrète dynamique.
     */
    public JwtService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * Récupère la clé de signature utilisée pour signer les tokens.
     */
    private Key getSigningKey() {
        return this.secretKey;
    }

    /**
     * Extrait le nom d'utilisateur du token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait une réclamation spécifique du token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Génère un token pour un utilisateur
     */
    public String generateToken(String email) {
        return generateToken(new HashMap<>(), email);
    }

    /**
     * Génère un token avec des réclamations supplémentaires
     */
    public String generateToken(Map<String, Object> extraClaims, String email) {
        return buildToken(extraClaims, email, JWT_EXPIRATION);
    }

    /**
     * Génère un token de rafraîchissement
     */
    public String generateRefreshToken(String email) {
        return buildToken(new HashMap<>(), email, REFRESH_EXPIRATION);
    }

    /**
     * Construit le token JWT
     */
    private String buildToken(
            Map<String, Object> extraClaims,
            String email,
            long expiration
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Vérifie si le token est valide
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vérifie si le token est expiré
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrait la date d'expiration du token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait toutes les réclamations du token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
