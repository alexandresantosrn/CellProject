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
	static Authentication getAuthentication(HttpServletRequest request) {
		var token = request.getHeader(HEADER_STRING);
		var secret = "segredo";

		if (token == null && secret == null) {
			return null;
		} else if (token != null) {
			token = token.replace("Bearer ", "");
		} else if (token == null) {
			token = secret;
		}		
		
		logger.info(token);
		return parseTokenSubject(token);
	}

	private static UsernamePasswordAuthenticationToken parseTokenSubject(String token) {

		var subject = "allexsantosrn@gmail.com";

		return new UsernamePasswordAuthenticationToken(subject, null, List.of());
	}
}
