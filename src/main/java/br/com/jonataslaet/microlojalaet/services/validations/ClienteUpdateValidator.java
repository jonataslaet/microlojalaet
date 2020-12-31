package br.com.jonataslaet.microlojalaet.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.jonataslaet.microlojalaet.controllers.ClienteDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.CustomFieldError;
import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.repositories.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	HttpServletRequest request;
	
	List<CustomFieldError> errors = new ArrayList<>();
	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> mapa = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.parseInt(mapa.get("id"));
		Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
		
		if (cliente != null && cliente.getId() != id) {
			errors.add(new CustomFieldError("email", "Email repetido"));
		}
		
		for (CustomFieldError customFieldError : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(customFieldError.getMessage()).addPropertyNode(customFieldError.getNome()).addConstraintViolation();
		}
		return errors.isEmpty();
	}
}
