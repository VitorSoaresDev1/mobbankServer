package br.com.mobbank.mobBankServer.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;

public class DepositDto {
	private int cardId;
	private LocalDateTime dataRealizacao = LocalDateTime.now();
	private double value;
	private int tipo;
	private int transferTo;
	
	public DepositDto(Deposit deposit) {
		this.cardId = deposit.getCardId();
		this.dataRealizacao = deposit.getDataRealizacao();
		this.value = deposit.getValue();
		this.tipo = deposit.getTipo();
		this.transferTo = deposit.getTransferTo();
	}

	public int getCardId() {
		return cardId;
	}

	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}
	
	public double getValue() {
		return value;
	}

	public int getTipo() {return tipo;}

	public int getTransferTo() {return transferTo;}

	public static List<DepositDto> converter(List<Deposit> deposits) {
		return deposits.stream().map(DepositDto::new).collect(Collectors.toList());
	}
}