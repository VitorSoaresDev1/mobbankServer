package br.com.mobbank.mobBankServer.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;
import br.com.mobbank.mobBankServer.model.User;

public class DepositForm {
	@NotNull @Length(min = 5)
	private String uuid;
	@NotNull
	private int cardId;
	@NotNull
	private double value;
	@NotNull
	private int tipo;
	private int transferTo;
	@NotNull @NotEmpty
	private String senha;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
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

	public int getTipo() {return tipo;}

	public void setTipo(int tipo) {this.tipo = tipo;}

	public int getTransferTo() {return transferTo;}

	public void setTransferTo(int transferTo) {this.transferTo = transferTo;}

	public Deposit converter() {return new Deposit(this.uuid, this.cardId,this.transferTo,this.tipo, this.value);}
}
