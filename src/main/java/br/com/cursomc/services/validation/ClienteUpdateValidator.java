package br.com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.dto.cliente.ClienteDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.exception.FieldMessage;

/**
 * Classe para validar clientes sendo atualizados
 *
 * @author Flavio Solci
 *
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	/** Usado para pegar os parametros da URL */
	@Autowired
	private HttpServletRequest request;

	/** Repositório para validar clientes */
	@Autowired
	private ClienteRepository repository;

	@Override
	public boolean isValid(final ClienteDTO dto, final ConstraintValidatorContext context) {

		final Integer id = getIDFromRequestParameters();
		final List<FieldMessage> listMessages = new ArrayList<>();

		final Cliente clienteComEmail = repository.findByEmail(dto.getEmail());
		if (clienteComEmail != null && !clienteComEmail.getId().equals(id)) {
			listMessages.add(new FieldMessage("email", "E-mail já cadastrado!"));
		}

		for (final FieldMessage fieldMessage : listMessages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
			.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();

		}
		return listMessages.isEmpty();
	}

	/**
	 * @return ID que vem como parâmetro na URL
	 */
	@SuppressWarnings("unchecked")
	private Integer getIDFromRequestParameters() {
		final Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		return Integer.parseInt(map.get("id"));
	}

}
