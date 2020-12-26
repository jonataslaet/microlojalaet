package br.com.jonataslaet.microlojalaet.domain;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private Integer codigo;
	private String descricao;

	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		TipoCliente tipoCliente = null;

		if (cod != null) {
			for (TipoCliente t : TipoCliente.values()) {
				if (cod.equals(t.getCodigo())) {
					tipoCliente = t;
					break;
				}
			}
		}
		return tipoCliente;
	}

}
