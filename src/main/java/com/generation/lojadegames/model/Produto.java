package com.generation.lojadegames.model;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produtos")
public class Produto {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nome;
	    private String descricao;
	    private BigDecimal preco;

	    @ManyToOne
	    @JsonIgnoreProperties("produto")
	    private Categoria categoria;

	    // Getters e Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }
	    public String getDescricao() { return descricao; }
	    public void setDescricao(String descricao) { this.descricao = descricao; }
	    public BigDecimal getPreco() { return preco; }
	    public void setPreco(BigDecimal preco) { this.preco = preco; }
	    public Categoria getCategoria() { return categoria; }
	    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
	
	
 


