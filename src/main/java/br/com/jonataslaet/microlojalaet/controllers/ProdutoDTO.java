package br.com.jonataslaet.microlojalaet.controllers;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.jonataslaet.microlojalaet.domain.Produto;

public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	@Size(min = 5, max = 80, message = "O tamanho do nome deve estar entre 5 e 80 caracteres")
	private String nome;
	private Double preco;
	
	public ProdutoDTO() {
		
	}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
