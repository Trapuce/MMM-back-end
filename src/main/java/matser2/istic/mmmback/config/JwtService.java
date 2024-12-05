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


/**
 * JWT (JSON Web Token) Service
 *
 * Handles JWT token generation, validation, and claim extraction
 * Provides methods for creating and managing authentication tokens
 */
@Service
public class JwtService {

    private final Key secretKey;
    private static final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; //10 hours
    private static final long REFRESH_EXPIRATION = 1000 * 60 * 60 * 24 * 7; //7 days

    /**
     * Constructor
     * Generates a dynamic secret key using HS256 algorithm
     */
    public JwtService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * Retrieves the signing key used for token signature
     *
     * @return Cryptographic key for token signing
     */
    private Key getSigningKey() {
        return this.secretKey;
    }

    /**
     * Extracts username (subject) from the JWT token
     *
     * @param token JWT token
     * @return Username contained in the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the token using a claims resolver
     *
     * @param token JWT token
     * @param claimsResolver Function to extract specific claim
     * @return Extracted claim value
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a standard JWT token for a user
     *
     * @param email User's email address
     * @return Generated JWT token
     */
    public String generateToken(String email) {
        return generateToken(new HashMap<>(), email);
    }

    /**
     * Generates a JWT token with additional claims
     *
     * @param extraClaims Additional claims to include in the token
     * @param email User's email address
     * @return Generated JWT token
     */
    public String generateToken(Map<String, Object> extraClaims, String email) {
        return buildToken(extraClaims, email, JWT_EXPIRATION);
    }

    /**
     * Generates a refresh token with longer expiration
     *
     * @param email User's email address
     * @return Generated refresh token
     */
    public String generateRefreshToken(String email) {
        return buildToken(new HashMap<>(), email, REFRESH_EXPIRATION);
    }

    /**
     * Builds a JWT token with specified claims, subject, and expiration
     *
     * @param extraClaims Additional claims
     * @param email User's email address
     * @param expiration Token expiration time in milliseconds
     * @return Compact JWT token string
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
     * Validates a JWT token
     *
     * @param token JWT token to validate
     * @param userDetails User details to compare against
     * @return True if token is valid, false otherwise
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
     * Checks if a token has expired
     *
     * @param token JWT token to check
     * @return True if token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the token
     *
     * @param token JWT token
     * @return Expiration date of the token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the JWT token
     *
     * @param token JWT token
     * @return All claims contained in the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
