package br.com.cellprojectback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class UsuarioController {

	@PostMapping("/recupera-senha")
	public ResponseEntity<String> recuperarSenha(@RequestParam String email) {	 
		
		if (!UsuarioRepository.hasUsuarioByEmail(email)) {			
			return new ResponseEntity<>("E-mail não localizado na base de dados.", HttpStatus.NOT_FOUND);		
		}		
		
	    return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");
	}
	
	@PostMapping("/realizar-login")
	public ResponseEntity<String> realizarLogin(@RequestParam String email, String senha) {	 
		
		
		
	    return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");
	}
	
	
	
	
		
//	@RequestMapping("/minha-rota")
//    public String minhaRota(@RequestParam String minhaString) {
//        return "Esta é uma resposta de texto simples." + minhaString;
//    }
}
