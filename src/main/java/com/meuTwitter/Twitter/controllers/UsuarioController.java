package com.meuTwitter.Twitter.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meuTwitter.Twitter.exeption.ResourceNotFoundException;
import com.meuTwitter.Twitter.model.Postagem;
import com.meuTwitter.Twitter.model.Usuario;
import com.meuTwitter.Twitter.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuario")
	public Page<Usuario> getUsuario(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public Usuario getOneUsuario(@PathVariable Integer usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(()-> 
				new  ResourceNotFoundException("página não encontrada" + usuarioId));
	}
	
	@GetMapping("/usuario/buscarEmail/{emailUsuario}")
	public Page<Usuario> getUsuarioByEmail(@PathVariable String emailUsuario,
			Pageable pageable) {
		return usuarioRepository.findByEmail(emailUsuario, pageable);
	}
 	
	@PostMapping("/usuario")
	public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping("/usuario/{usuarioId}")
	public Usuario updateusuario(@PathVariable Integer usuarioId,
			@Valid @RequestBody Usuario usuarioRequest) {
		return usuarioRepository.findById(usuarioId)
				.map(usuario -> {
					usuario.setNomeDeUsuario(usuarioRequest.getNomeDeUsuario());
					usuario.setEmail(usuarioRequest.getEmail());
					usuario.setTelefone(usuarioRequest.getTelefone());
					usuario.setSenha(usuarioRequest.getSenha());
					
					
					return usuarioRepository.save(usuario);
				}).orElseThrow(()-> 
				new  ResourceNotFoundException("página não encontrada" + usuarioId));
	}
	
	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> delete(@PathVariable Integer usuarioId){
		 return usuarioRepository.findById(usuarioId)
				 .map(usuario -> {
					 usuarioRepository.delete(usuario);
					 return ResponseEntity.ok().build();
				 }).orElseThrow(()-> 
					new  ResourceNotFoundException("página não encontrada" + usuarioId));
	}
		
	@PostMapping("/usuario/{usuarioId}/savePost")
	public Usuario salvarPostagen(@PathVariable Integer usuarioId,
		@Valid	@RequestBody Postagem postagem) {
		return usuarioRepository.findById(usuarioId)
				.map(usuario -> {
					usuario.addPostagns(postagem);
					return usuarioRepository.save(usuario);
				}).orElseThrow(()-> 
				new  ResourceNotFoundException("página não encontrada cana" + usuarioId));
	}
	
	@PostMapping("/usuario/logar")
	public Usuario LoginMei(@RequestBody Map<String, String> params) {
		 
		String email = params.get("email");
		String senha = params.get("senha");
		
		return usuarioRepository.findByEmailAndSenha(email, senha);
	}
}
