package br.com.jonataslaet.microlojalaet.controllers;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.jonataslaet.microlojalaet.services.validations.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	@Size(min = 5, max = 120, message = "O tamanho do nome deve estar entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	private String senha;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	private String logradouro;
	private String numero;
	private String complemento;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	private String bairro;
	
	@NotEmpty(message = "O preenchimento do nome é obrigatório")
	private String cep;
	
	private Integer cidadeId;
	
	@NotEmpty(message = "O primeiro número de contato é obrigatório.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	public ClienteNewDTO() {
		
	}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
}
