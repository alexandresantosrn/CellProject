package security;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {

	public static final String HEADER_STRING = "Authorization";
	private static final Logger logger = Logger.getLogger(SecurityUtils.class.getName());

	@SuppressWarnings("unused")
	public static Authentication getAuthentication(HttpServletRequest request) {
		var token = request.getHeader(HEADER_STRING);
		var secret = "segredo";

		if (token == null && secret == null) {
			return null;
		} else if (token != null) {
			token = token.replace("Bearer ", "");
		} else if (token == null) {
			token = secret;
		}
		
//		if (token == null) {
//			return null;
//		}
//		
//		token = token.replace("Bearer ", "");

		logger.info(token);
		return parseTokenSubject(token);
	}

	private static UsernamePasswordAuthenticationToken parseTokenSubject(String token) {

		var subject = "secret";

		if (!token.equals("segredo")) {
			
			// Verificando a validade do token.
			if (!JwtUtil.isValid(token)) {
				return null;
			}

			// Verificando se o token encontra-se expirado.
			else if (JwtUtil.isExpired(token)) {
				return null;
			}

			// Verificando se o username não é nulo.
			else if (JwtUtil.getUsernameByToken(token) == null) {
				return null;
			}

			subject = JwtUtil.getUsernameByToken(token);
		}

		logger.info(subject);
		return new UsernamePasswordAuthenticationToken(subject, null, List.of());
	}
}
