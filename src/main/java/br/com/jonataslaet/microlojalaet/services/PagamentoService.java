package br.com.jonataslaet.microlojalaet.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.domain.PagamentoComBoleto;

@Service
public class PagamentoService {

	public void preencherDataVencimento(PagamentoComBoleto pagamentoEncontrado, Date instante) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instante);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoEncontrado.setDataVencimento(calendar.getTime());
	}

}
