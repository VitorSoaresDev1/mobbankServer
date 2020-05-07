package br.com.mobbank.mobBankServer.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;

public class DepositDto {
	private Card card;
	private LocalDateTime dataRealizacao = LocalDateTime.now();
	private double value;
	
	public DepositDto(Deposit deposit) {
		this.card = deposit.getCard();
		this.dataRealizacao = deposit.getDataRealizacao();
		this.value = deposit.getValue();
	}

	public Card getCard() {
		return card;
	}

	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}
	
	public double getValue() {
		return value;
	}
	
	public static List<DepositDto> converter(List<Deposit> deposits) {
		return deposits.stream().map(DepositDto::new).collect(Collectors.toList());
	}
}