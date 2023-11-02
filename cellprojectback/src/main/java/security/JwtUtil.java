package security;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

	private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());
	private static SecretKey key = Jwts.SIG.HS256.key().build();
	private static final long EXPIRATION_TIME = 3600000;

	public static String generateToken(String username) {

		Date now = new Date();
		Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

		return Jwts.builder().subject(username).expiration(expiration).notBefore(now).issuedAt(now)
				.id(UUID.randomUUID().toString()).signWith(key).compact();
	}

	public static void isValid(String word) {

		try {
			Jwts.parser().verifyWith(key).build().parseSignedClaims(word);
			logger.info("Token é valido!");

		} catch (JwtException e) {
			logger.info("Token não é válido");
		}

	}

}
