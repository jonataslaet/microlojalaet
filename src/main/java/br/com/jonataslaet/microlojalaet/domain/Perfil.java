package br.com.jonataslaet.microlojalaet.domain;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), 
	CLIENTE(2, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {
		Perfil estadoPagamento = null;

		if (cod != null) {
			for (Perfil t : Perfil.values()) {
				if (cod.equals(t.getCodigo())) {
					estadoPagamento = t;
					break;
				}
			}
		}
		return estadoPagamento;
	}

}
