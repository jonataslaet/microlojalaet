package br.com.jonataslaet.microlojalaet.domain;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		EstadoPagamento estadoPagamento = null;

		if (cod != null) {
			for (EstadoPagamento t : EstadoPagamento.values()) {
				if (cod.equals(t.getCodigo())) {
					estadoPagamento = t;
					break;
				}
			}
		}
		return estadoPagamento;
	}

}
