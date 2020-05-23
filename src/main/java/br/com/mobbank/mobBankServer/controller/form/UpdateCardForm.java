package br.com.mobbank.mobBankServer.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.User;
import br.com.mobbank.mobBankServer.repository.CardRepository;

public class UpdateCardForm {
	@NotNull @NotEmpty
	private Long id;
	@NotNull @NotEmpty
	private String numeroConta;
	@NotNull @NotEmpty
	private User ownerId;
	private double saldo;
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}
	
	public void setSaldO(double saldo) {
		this.saldo = saldo;
	}

	public Card atualizar(Long id, CardRepository cardRepository) {
		Card card = cardRepository.getOne(id);
		card.setId(this.id);
		card.setNumeroConta(this.numeroConta);
		card.setUser(this.ownerId);
		card.setSaldo(this.saldo);
		return card;
	}
}
