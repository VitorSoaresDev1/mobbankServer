package br.com.mobbank.mobBankServer.config.validacao;

public class DtoFormError {

	private String campo;
	private String erro;
	
	public DtoFormError(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	public String getCampo() {
		return campo;
	}
	public String getErro() {
		return erro;
	}
	
}
