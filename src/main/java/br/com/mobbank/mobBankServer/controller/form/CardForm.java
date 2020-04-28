package br.com.mobbank.mobBankServer.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.User;
import br.com.mobbank.mobBankServer.repository.UserRepository;

public class CardForm {

	@NotNull @NotEmpty
	private String numeroConta;
	@NotNull 
	private User ownerId;
	private double saldo;
	

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public double getSaldo() {
		return this.saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Card converter(UserRepository userRepository) throws Exception {
		Optional<User> option = userRepository.findById(this.ownerId.getId());
		if(option.isPresent()) {
			return new Card(this.numeroConta, option, this.saldo);			
		}else {
			throw new Exception("Usuario não encontrado");
		}
	}
	
}
