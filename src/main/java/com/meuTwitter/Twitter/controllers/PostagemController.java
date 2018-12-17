package com.meuTwitter.Twitter.controllers;

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
import com.meuTwitter.Twitter.repositories.PostagemRepository;

@RestController
public class PostagemController {

	@Autowired
	PostagemRepository postagemRepository;
	
	@GetMapping("/postagem")
	public Page<Postagem> getPost(Pageable pageable){
		return postagemRepository.findAll(pageable);
	}
	
	@GetMapping("/postagen/{postagenId}")
	public Postagem getOneUsuario(@PathVariable Integer postagenId) {
		return postagemRepository.findById(postagenId)
				.orElseThrow(()-> 
				new  ResourceNotFoundException("página não encontrada" + postagenId));
	}
	
	@PostMapping("/postagem")
	public Postagem createPostagem(@Valid @RequestBody Postagem postagem) {
		return postagemRepository.save(postagem);
	}

	@PutMapping("/postagem/{postagemId}")
	public Postagem updateusuario(@PathVariable Integer postagemId,
			@Valid @RequestBody Postagem postagemRequest) {
		return postagemRepository.findById(postagemId)
				.map(postagem -> {
					postagem.setDescricao(postagemRequest.getDescricao());
					postagem.setHorario(postagemRequest.getHorario());

					return postagemRepository.save(postagem);
				}).orElseThrow(()-> 
				new  ResourceNotFoundException("página não encontrada" + postagemId));
	}
	
	@DeleteMapping("/postagem/{postagemId}")
	public ResponseEntity<?> delete(@PathVariable Integer postagemId){
		 return postagemRepository.findById(postagemId)
				 .map(postagem -> {
					 postagemRepository.delete(postagem);
					 return ResponseEntity.ok().build();
				 }).orElseThrow(()-> 
					new  ResourceNotFoundException("página não encontrada" + postagemId	));
	}
	
}
