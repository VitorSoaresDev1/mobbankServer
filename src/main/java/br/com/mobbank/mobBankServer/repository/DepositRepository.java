package br.com.mobbank.mobBankServer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mobbank.mobBankServer.model.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long>{
	Optional<List<Deposit>> findByCardId(int id);
}
