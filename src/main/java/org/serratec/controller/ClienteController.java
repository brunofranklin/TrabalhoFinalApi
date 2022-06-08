package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.dto.ClienteDTO;
import org.serratec.dto.ClienteInserirDTO;
import org.serratec.exception.CpfException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.EmailException;
import org.serratec.model.Cliente;
import org.serratec.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping
    @ApiOperation(value = "Lista todos os clientes", notes = "Listagem de clientes")
    public ResponseEntity<Object> listarTodos() throws CustomNoContentException{
        List<ClienteDTO> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/{cpf}")
    @ApiOperation(value = "Lista cliente por cpf", notes = "Listagem de clientes por cpf")
    public ResponseEntity<Object> buscarPorCpf(@PathVariable String cpf)throws CustomNotFoundException{
        
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Cadastra cliente", notes = "Cadastrar clientes")
    public ResponseEntity<Object> inserir(@Valid @RequestBody ClienteInserirDTO clienteInserirDTO)
    throws EmailException, CpfException, CustomNotFoundException{
        
        ClienteDTO clienteDTO = clienteService.inserir(clienteInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PutMapping("/{cpf}")
    @ApiOperation(value = "Atualizar cliente por cpf", notes = "Atualiza cliente por cpf")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody ClienteInserirDTO clienteInserirDTO, 
    @PathVariable String cpf) throws CustomNotFoundException{
        
        ClienteDTO clienteDTO = clienteService.atualizar(clienteInserirDTO, cpf);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{cpf}")
    @ApiOperation(value = "Deletar cliente por cpf", notes = "Delta cliente por cpf")
    public ResponseEntity<Object> deletar(@PathVariable String cpf) throws CustomNotFoundException{
        clienteService.deletar(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cpf);
    }

}
