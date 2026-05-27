package com.generation.lojadegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	// Buscar todos
	 @GetMapping
	    public ResponseEntity<List<Produto>> getAll() {
	        return ResponseEntity.ok(produtoRepository.findAll());
	}
	 
	 //  Buscar por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Produto> getById(@PathVariable Long id) {
	        return produtoRepository.findById(id)
	                .map(resposta -> ResponseEntity.ok(resposta))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    //  Buscar por Nome (Termo específico)
	    @GetMapping("/nome/{nome}")
	    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
	        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	    }

	    //  Cadastrar
	    @PostMapping
	    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	    }

	    //  Atualizar
	    @PutMapping
	    public ResponseEntity<Produto> put(@RequestBody Produto produto) {
	        return produtoRepository.findById(produto.getId())
	                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }

	    //  Deletar
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> delete(@PathVariable Long id) {
	        return produtoRepository.findById(id)
	                .map(resposta -> {
	                    produtoRepository.deleteById(id);
	                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	                })
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	 
}
