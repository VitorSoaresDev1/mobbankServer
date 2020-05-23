package br.com.mobbank.mobBankServer.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Deposit {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	private int cardId;
	private int transferTo;
	private int tipo;
	private LocalDateTime dataRealizacao = LocalDateTime.now();
	private double value;
	
	public Deposit() {
	}

	public Deposit(String uuid,int cardId, int transferTo, int tipo ,double value) {
		this.uuid  = uuid;
		this.cardId  = cardId;
		this.transferTo = transferTo;
		this.tipo = tipo;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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

	public LocalDateTime getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(LocalDateTime dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getTransferTo() {return transferTo;}

	public void setTransferTo(int transferTo) {this.transferTo = transferTo;}

	public int getTipo() {return tipo;}

	public void setTipo(int tipo) {this.tipo = tipo;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Deposit deposit = (Deposit) o;
		return cardId == deposit.cardId &&
				transferTo == deposit.transferTo &&
				tipo == deposit.tipo &&
				Double.compare(deposit.value, value) == 0 &&
				Objects.equals(id, deposit.id) &&
				Objects.equals(uuid, deposit.uuid) &&
				Objects.equals(dataRealizacao, deposit.dataRealizacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, uuid, cardId, transferTo, tipo, dataRealizacao, value);
	}

	@Override
	public String toString() {
		return "Deposit{" +
				"id=" + id +
				", uuid='" + uuid + '\'' +
				", cardId=" + cardId +
				", transferTo=" + transferTo +
				", tipo=" + tipo +
				", dataRealizacao=" + dataRealizacao +
				", value=" + value +
				'}';
	}
}
