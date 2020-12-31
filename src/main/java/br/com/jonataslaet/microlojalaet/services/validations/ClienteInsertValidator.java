package br.com.jonataslaet.microlojalaet.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//import br.com.jonataslaet.microlojalaet.services.validations.ClienteInsert;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jonataslaet.microlojalaet.controllers.ClienteNewDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.CustomFieldError;
import br.com.jonataslaet.microlojalaet.domain.TipoCliente;
import br.com.jonataslaet.microlojalaet.repositories.ClienteRepository;
import br.com.jonataslaet.microlojalaet.services.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	@Autowired
	ClienteRepository clienteRepository;
	
	List<CustomFieldError> errors = new ArrayList<>();
	List<CustomFieldError> auxiliares = new ArrayList<>();
	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		
		if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			errors.add(new CustomFieldError("cpfOuCnpj", "CPF inválido"));
		}
		
		else if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			errors.add(new CustomFieldError("cpfOuCnpj", "CNPJ inválido"));
		}
		
		if (clienteRepository.existsByEmail(clienteNewDTO.getEmail())) {
			errors.add(new CustomFieldError("email", "Email repetido"));
		}
		
		for (CustomFieldError customFieldError : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(customFieldError.getMessage()).addPropertyNode(customFieldError.getNome()).addConstraintViolation();
		}
		auxiliares.addAll(errors);
		errors.clear();
		return auxiliares.isEmpty();
	}
}
