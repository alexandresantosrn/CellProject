package security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtil {

	private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static String cryptPassword(String senha) {

		return bCryptPasswordEncoder.encode(senha);
	}

	public static boolean isMatchPassword(String senha, String hash) {

		return bCryptPasswordEncoder.matches(senha, hash);
	}
}
