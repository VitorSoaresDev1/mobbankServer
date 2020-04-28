package br.com.mobbank.mobBankServer.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mobbank.mobBankServer.model.User;
import br.com.mobbank.mobBankServer.repository.UserRepository;

public class UpdateUserForm {
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 10)
	private String email;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User atualizar(Long id, UserRepository userRepository) {
		User usuario = userRepository.getOne(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		return usuario;
	}
}
