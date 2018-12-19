package com.meuTwitter.Twitter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuTwitter.Twitter.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
  Page<Usuario> findByEmail(String email, Pageable pageable);
  public Usuario findByEmailAndSenha (String email, String senha);
}
