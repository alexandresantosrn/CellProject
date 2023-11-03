package security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.GenericFilter;
import jakarta.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends GenericFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
			jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {

		var auth = SecurityUtils.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}

}
