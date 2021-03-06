package br.com.cursomc.services;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.cursomc.domain.cliente.Cidade;
import br.com.cursomc.domain.cliente.Cliente;
import br.com.cursomc.domain.cliente.Endereco;
import br.com.cursomc.domain.cliente.TipoCliente;
import br.com.cursomc.domain.user.Perfil;
import br.com.cursomc.dto.cliente.ClienteDTO;
import br.com.cursomc.dto.cliente.ClienteNewDTO;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.security.UserSpringSecurity;
import br.com.cursomc.services.amazon.S3Service;
import br.com.cursomc.services.exception.AuthorizationException;
import br.com.cursomc.services.exception.DataIntegrityException;
import br.com.cursomc.services.exception.ObjectNotFoundException;

/**
 * Serviço de acesso aos Clientes
 *
 * @author Flavio Solci
 *
 */
@Service
public class ClienteService {

	/** Acesso ao BD */
	@Autowired
	private ClienteRepository repository;

	/** Salva os endereços do cliente novo */
	@Autowired
	private EnderecoRepository endrecoRepo;

	/** Encripta a senha do cliente */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/** Envia um arquivo para s3 */
	@Autowired
	private S3Service s3Service;

	/**
	 * Busca um cliente pelo ID
	 *
	 * @param id ID do cliente
	 * @return Cliente ou lança uma exceção se não encontrado
	 */
	public Cliente find(final Integer id) {
		// O Cliente pode acessar apenas o seu cadastros.
		// Exceto se o cliente tiver perfil de ADMIN
		final UserSpringSecurity user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}

		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente com o ID " + id + " não existe!"));
	}

	/**
	 * Insere um novo cliente
	 *
	 * @param cliente Cliente a ser inserido
	 * @return Cliente inserido
	 */
	public Cliente insert(final Cliente cliente) {
		cliente.setId(null);
		final List<Endereco> enderecosSalvos = endrecoRepo.saveAll(cliente.getEnderecos());
		cliente.getEnderecos().clear();
		cliente.getEnderecos().addAll(enderecosSalvos);
		return repository.save(cliente);
	}

	/**
	 * Atualiza um Cliente, se ele não existir lança uma exceção
	 *
	 * @param cliente Cliente para ser atualizado
	 * @return Cliente atualizado
	 */
	public Cliente update(final ClienteDTO clienteDTO) {
		// se o cliente não for encontrado lança uma exceção
		final Cliente clienteFound = find(clienteDTO.getId());
		updateClienteWithDTO(clienteFound, clienteDTO);
		return repository.save(clienteFound);
	}

	/**
	 * Deleta um cliente, se ele não existir lança uma exceção
	 *
	 * @param id Id para ser deletado
	 */
	public void delete(final Integer id) {
		// se o cliente não for encontrada lança uma exceção
		find(id);
		try {
			repository.deleteById(id);
		} catch (final DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente com entidades relacionadas!");
		}
	}

	/**
	 * @return Todas os Clientes do DB
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/**
	 * Encontra todas os clientes com paginação
	 *
	 * @param page         Qual página pegar (inicia em zero)
	 * @param linesPerPage quantos registros por pagina
	 * @param orderBy      Ordenação
	 * @param direction    Direção da ordem
	 * @return Página com Clientes
	 */
	public Page<Cliente> findWithPage(final Integer page, final Integer linesPerPage, final String orderBy,
			final Direction direction) {
		final PageRequest pageRequest = PageRequest.of(page, linesPerPage, direction, orderBy);
		return repository.findAll(pageRequest);
	}

	// =============================
	// == DTO
	// =============================

	/**
	 * Construtor com parâmetro do objeto dto
	 *
	 * @param clienteNewDTO dto para ser transformado em cliente
	 */
	public Cliente fromDTO(final ClienteNewDTO clienteNewDTO) {
		final Cliente cliente = new Cliente(clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
				clienteNewDTO.getCpfOuCnpj(), TipoCliente.toTipoCliente(clienteNewDTO.getTipo()),
				passwordEncoder.encode(clienteNewDTO.getSenha()));

		cliente.getEnderecos().add(new Endereco(clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
				clienteNewDTO.getBairro(), clienteNewDTO.getBairro(), new Cidade(clienteNewDTO.getCidadeId())));
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());

		if (StringUtils.isNotEmpty(clienteNewDTO.getTelefone2())) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}

		if (StringUtils.isNotEmpty(clienteNewDTO.getTelefone2())) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}

		return cliente;
	}

	// =============================
	// == S3
	// =============================

	/**
	 * Envia a foto do usuário para o S3
	 *
	 * @param file Foto do cliente
	 * @return URI aonde a foto foi salva
	 */
	public URI uploadProfilePicture(final MultipartFile file) {
		return s3Service.uploadFile(file);
	}

	// =============================
	// == HELPER
	// =============================

	/**
	 * Atualiza um cliente do BD com as informações do DTO, assim o cliente será
	 * atualizado apenas com as informações relevantes e o resto permanecerá igual
	 *
	 * @param clienteFound Cliente encontrando no BD
	 * @param clienteDTO   Cliente vindo do serviço REST para ser atualizado
	 */
	private void updateClienteWithDTO(final Cliente clienteFound, final ClienteDTO clienteDTO) {
		clienteFound.setNome(clienteDTO.getNome());
		clienteFound.setEmail(clienteDTO.getEmail());
	}

}
