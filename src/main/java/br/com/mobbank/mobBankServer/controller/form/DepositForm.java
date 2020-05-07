package br.com.mobbank.mobBankServer.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;
import br.com.mobbank.mobBankServer.model.User;

public class DepositForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String uuid;
	@NotNull @NotEmpty
	private Card card;
	@NotNull @NotEmpty
	private User owner;
	@NotNull @NotEmpty
	private double value;
	@NotNull @NotEmpty
	private String senha;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Deposit converter() {
		return new Deposit(this.uuid, this.card, this.owner, this.value);
	}
}
