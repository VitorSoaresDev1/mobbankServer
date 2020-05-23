package br.com.mobbank.mobBankServer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mobbank.mobBankServer.model.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

	Optional<List<Card>> findByUser_id(Long id);

}
