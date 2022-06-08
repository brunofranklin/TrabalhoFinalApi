package org.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

//import javax.mail.MessagingException;

import org.serratec.dto.ClienteDTO;
//import org.serratec.config.MailConfig;
import org.serratec.dto.ClienteInserirDTO;
import org.serratec.exception.CpfException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.EmailException;
import org.serratec.model.Cliente;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    // @Autowired
    // private MailConfig mailConfig;


    //LISTAR TODOS OS CLIENTES CADASTRADOS
    public List<ClienteDTO> listar(){
        if(clienteRepository.findAll().isEmpty()){
            throw new CustomNoContentException(null);
        }     
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
    }

    //LISTAR CLIENTE POR CPF
    public Cliente buscarPorCpf(String cpf){     
        if(clienteRepository.findByCpf(cpf) != null){
            return clienteRepository.findByCpf(cpf);
        }
        else{
            throw new CustomNotFoundException("Cliente com cpf'"+cpf+"' não foi encontrado");
        }

    }

    //CADASTRAR CLIENTE
    public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO){
        if(clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null){
            throw new EmailException("Email já cadastrado!");
        }
        else if(clienteRepository.findByCpf(clienteInserirDTO.getCpf()) != null){
            throw new CpfException("Cpf já cadastrado!");
        }

        Cliente cliente = new Cliente();
        enderecoService.buscar(clienteInserirDTO.getCep());
        
        cliente.setNome(clienteInserirDTO.getNome());
        cliente.setCpf(clienteInserirDTO.getCpf());
        cliente.setEmail(clienteInserirDTO.getEmail());
        cliente.setEndereco(enderecoRepository.findByCep(clienteInserirDTO.getCep()));
        
        cliente = clienteRepository.save(cliente);
        // try {
        //     mailConfig.enviarEmailHTML(cliente.getEmail(), "Cadastro de cliente!",
        //     emailCadastro(clienteInserirDTO.getNome(), clienteInserirDTO.getEmail()));
        // } catch (MessagingException e) {
        //     e.getMessage();
        // }

        return new ClienteDTO(cliente);
    }

    //ATUALIZAR CLIENTE POR CPF
    public ClienteDTO atualizar(ClienteInserirDTO clienteInserirDTO, String cpf){
        Cliente cliente = new Cliente();
        if(clienteRepository.findByCpf(cpf) != null){
            cliente.setId(clienteRepository.findByCpf(cpf).getId()); 
            clienteRepository.deleteByCpf(cpf); 
            enderecoService.buscar(clienteInserirDTO.getCep());     
            
            cliente.setNome(clienteInserirDTO.getNome());
            cliente.setCpf(clienteInserirDTO.getCpf());
            cliente.setEmail(clienteInserirDTO.getEmail());
            cliente.setEndereco(enderecoRepository.findByCep(clienteInserirDTO.getCep())); 
            clienteRepository.save(cliente);             
            
            // try {
            //     mailConfig.enviarEmailHTML(cliente.getEmail(), "Atualização de cadastro!",
            //      emailAtualizarCadastro(cliente.getNome(), cliente.getEmail()));
            // } catch (MessagingException e) {
            //     e.getMessage();
            // }   
            return new ClienteDTO(cliente);        
        }   
                   
	 	throw new CustomNotFoundException("Cliente com cpf '" + cpf + "' não foi encontrado");
    }

    //DELETAR CLIENTE POR CPF
    public void deletar(String cpf){
        if(clienteRepository.findByCpf(cpf) != null){
            clienteRepository.deleteByCpf(cpf);
        }else{
            throw new CustomNotFoundException("Cliente com cpf '" + cpf + "' não foi encontrado");
        }
    }

    //TEXTO DO EMAIL DE CADASTRO
    public String emailCadastro(String nome, String email){
        String textoHtml = "<h1 align='center'>Olá," + nome + " Obrigado por se cadastrar em nossa loja!</h1>" +
		"<div>" +
			"<h3>"+
				"<p>Nome: " + nome +"<p>"+
				"<p>E-Mail: "+email+" <p>"+
			"</h3>"+
		"</div>"+
		"<div>"+
			"<h2 align='center'>"+
				"Atenciosamente Loja"+
			"</h2>"+
		"</div>";

        return textoHtml;
    }

    //TEXTO DO EMAIL DE ATUALIZAÇÃO DE CADASTRO
    public String emailAtualizarCadastro(String nome, String email){
        String textoHtml = "<h1 align='center'>Olá," + nome + ", seu cadastro foi atualizado!</h1>" +
		"<div>" +
			"<h3>"+
				"<p>Nome: " + nome +"<p>"+
				"<p>E-Mail: "+email+" <p>"+
			"</h3>"+
		"</div>"+
		"<div>"+
			"<h2 align='center'>"+
				"Atenciosamente Loja"+
			"</h2>"+
		"</div>";

        return textoHtml;
    }


    
}
