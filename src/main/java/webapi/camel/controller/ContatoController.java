package webapi.camel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapi.camel.model.Contato;
import webapi.camel.repository.ContatoReposirory;

@RestController
@RequestMapping("/api/contato")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatoController {
    
    @Autowired
    ContatoReposirory contatoReposirory;

    @GetMapping("/todos")
	public ResponseEntity<List<Contato>> getAll() {
		return ResponseEntity.ok(contatoReposirory.findAll());
	}

    @PostMapping("/novoContato")
	public ResponseEntity<Contato> postCalcFaleMais(@RequestBody Contato contato) {

        return ResponseEntity.status(HttpStatus.CREATED).body(contatoReposirory.save(contato));
    }

}
