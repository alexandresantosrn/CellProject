package br.com.cellprojectback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UsuarioController {

	@RequestMapping("/recupera-senha")
	public ResponseEntity<String> recuperarSenha(@RequestParam String email) {	 
		System.out.println(email);
	    return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");
	}
		
//	@RequestMapping("/minha-rota")
//    public String minhaRota(@RequestParam String minhaString) {
//        return "Esta é uma resposta de texto simples." + minhaString;
//    }
}
