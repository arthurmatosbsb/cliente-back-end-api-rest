package com.arthurmatosbsb.ClientesServicos.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cliente {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@Column(nullable = false, length = 11)
	@CPF(message = "CPF Inválido!")
	@NotEmpty(message = "Preencha o campo CPF")
	private String cpf;
	
	
	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cadastroData = LocalDate.now();
	
	@Column(nullable = false, length = 100)
	@NotEmpty(message = "Preencha o campo nome!")
	private String nome;
	public Cliente () {
		
	}
	
	public Cliente(String cpf, LocalDate cadastroData, String nome) {
		this.cpf = cpf;
		this.cadastroData = cadastroData;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getCadastroData() {
		return cadastroData;
	}

	public void setCadastroData(LocalDate cadastroData) {
		this.cadastroData = cadastroData;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", cadastroData=" + cadastroData + ", nome=" + nome + "]";
	}
	
	
	
}
