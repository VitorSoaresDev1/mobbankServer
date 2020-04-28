package br.com.mobbank.mobBankServer.repository;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mobbank.mobBankServer.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	Optional<User> findById(@NotNull @NotEmpty Long ownerId);

}
