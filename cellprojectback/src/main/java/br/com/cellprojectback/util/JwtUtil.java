package br.com.cellprojectback.util;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
	
	static SecretKey key = Jwts.SIG.HS256.key().build();

    public static String generateToken(String username) {    

    	return Jwts.builder().subject("Joe").signWith(key).compact(); 
    }

	public static void isValido(String pass) {
		
		try {

		    Jwts.parser().verifyWith(key).build().parseSignedClaims(pass);			
		    System.out.println("Valido!");

		} catch (JwtException e) {

		    //don't trust the JWT!
		}
		
	}

}
