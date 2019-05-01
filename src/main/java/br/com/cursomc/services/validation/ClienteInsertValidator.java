package br.com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cursomc.dto.cliente.ClienteNewDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.resources.exception.FieldMessage;
import br.com.cursomc.services.validation.utils.CPFCNPJUtils;

/**
 * Classe para validar clientes novos
 *
 * @author Flavio Solci
 *
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	/** Repositório para validar clientes */
	@Autowired
	private ClienteRepository repository;

	@Override
	public boolean isValid(final ClienteNewDTO dto, final ConstraintValidatorContext context) {

		final List<FieldMessage> listMessages = new ArrayList<>();

		if (!CPFCNPJUtils.isValid(dto.getCpfOuCnpj())) {
			listMessages.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ do Cliente é inválido!"));
		}

		if (repository.findByEmail(dto.getEmail()) != null) {
			listMessages.add(new FieldMessage("email", "E-mail já cadastrado!"));
		}

		for (final FieldMessage fieldMessage : listMessages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
			.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();

		}
		return listMessages.isEmpty();
	}

}
