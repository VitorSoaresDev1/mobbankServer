package br.com.mobbank.mobBankServer.model;

import java.time.LocalDateTime;

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
	@ManyToOne(cascade = {CascadeType.ALL})
	private User owner;
	@ManyToOne(cascade = {CascadeType.ALL})
	private Card card;
	private LocalDateTime dataRealizacao = LocalDateTime.now();
	private double value;
	
	public Deposit() {
	}

	public Deposit(String uuid,Card card,User owner,double value) {
		this.uuid  = uuid;
		this.card  = card;
		this.owner = owner;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Deposit other = (Deposit) obj;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deposit [id=" + id + ", owner=" + owner + ", card=" + card + ", dataRealizacao=" + dataRealizacao
				+ ", value=" + value + "]";
	}
}
