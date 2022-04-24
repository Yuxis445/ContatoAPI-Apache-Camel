package webapi.camel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webapi.camel.model.Contato;

public interface ContatoReposirory extends JpaRepository<Contato, Long>{
    
}
