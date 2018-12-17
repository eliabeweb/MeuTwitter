package com.meuTwitter.Twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuTwitter.Twitter.model.Postagem;
@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{

}
