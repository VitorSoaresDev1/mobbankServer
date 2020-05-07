package br.com.mobbank.mobBankServer.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.User;

public class CardDto {
	private Long id;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataExpiracao;
	private String numeroConta;
	private User ownerId;
	private double saldo;
	
	
	public CardDto(Card card) {
		this.id = card.getId();
		this.dataCriacao = card.getDataCriacao();
		this.dataExpiracao = card.getDataExpiracao();
		this.numeroConta = card.getNumeroConta();
		this.ownerId = card.getOwner();
		this.saldo = card.getSaldo();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public User getOwnerId() {
		return ownerId;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public static List<CardDto> converter(List<Card> cards) {
		return cards.stream().map(CardDto::new).collect(Collectors.toList());
	}
	
	
}
