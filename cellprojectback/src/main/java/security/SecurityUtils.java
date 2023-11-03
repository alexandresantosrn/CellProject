package security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {

	public static final String HEADER_STRING = "Authorization";

    static Authentication getAuthentication(HttpServletRequest request) {
        var token2 = request.getHeader(HEADER_STRING);
        var token = "xxxxxxxxxxx";
        
        System.out.println(token);
//        
        if (token == null) {
	       return null;
	    }        
       
        //token2 = token2.replace("Bearer ", "");
        
        //System.out.println(token2);
        return parseTokenSubject(token);
    }
    
    private static UsernamePasswordAuthenticationToken parseTokenSubject(String token) {
        
        var subject = "allexsantosrn@gmail.com";
        System.out.println(token);
        return new UsernamePasswordAuthenticationToken(subject, null, List.of());
    }
}
