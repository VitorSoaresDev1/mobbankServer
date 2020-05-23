package br.com.mobbank.mobBankServer.model;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Card {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String numeroConta;
	@ManyToOne(cascade = {CascadeType.ALL})
	private User user;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private LocalDateTime dataExpiracao = LocalDateTime.now().plusYears(4);
	private double saldo;
	
	
	public Card() {
	}
	
	public Card( String numeroConta, Optional<User> option, double saldo) {
		this.numeroConta = numeroConta;
		this.user = option.get();
		this.saldo = saldo;
	}
	public Card( String numeroConta, User user, double saldo) {
		this.numeroConta = numeroConta;
		this.user = user;
		this.saldo = saldo;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}


	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((dataExpiracao == null) ? 0 : dataExpiracao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroConta == null) ? 0 : numeroConta.hashCode());
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
		Card other = (Card) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (dataExpiracao == null) {
			if (other.dataExpiracao != null)
				return false;
		} else if (!dataExpiracao.equals(other.dataExpiracao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroConta == null) {
			if (other.numeroConta != null)
				return false;
		} else if (!numeroConta.equals(other.numeroConta))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cartao [numeroConta=" + numeroConta + ", Owner=" + user + ", dataCriacao=" + dataCriacao
				+ ", dataExpiracao=" + dataExpiracao + "]";
	}
	
	
	

}
