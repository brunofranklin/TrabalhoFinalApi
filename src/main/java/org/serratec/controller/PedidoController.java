package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.dto.PedidoDTO;
import org.serratec.dto.PedidoInserirDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.EnumValidationException;
import org.serratec.exception.PedidoException;
import org.serratec.exception.ProdutoException;
import org.serratec.model.Pedido;
import org.serratec.service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
      
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    }) 
    @GetMapping
    @ApiOperation(value = "Lista pedidos", notes = "Listagem de pedidos")
    public ResponseEntity<List<PedidoDTO>> listar() throws CustomNoContentException{
        List<PedidoDTO> pedidoDTOs = pedidoService.listar();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/{id}")
    @ApiOperation(value = "Lista pedido por id", notes = "Listagem de pedidos por id")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) throws CustomNotFoundException{
        Pedido pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Cadastrar pedido", notes = "Cadastra um pedido")
    public ResponseEntity<Object> inserir(@Valid @RequestBody PedidoInserirDTO pedidoInserirDTO)
    throws EnumValidationException, PedidoException{
        
        PedidoInserirDTO pedDTO = pedidoService.inserir(pedidoInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar pedido", notes = "Atualiza pedido por id")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody PedidoInserirDTO pedidoInserirDTO, @PathVariable Long id)
    throws ProdutoException, CustomNotFoundException{
        
        PedidoDTO pedidoDTO = pedidoService.atualizar(pedidoInserirDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar pedido", notes = "Deleta pedido por id")
    public ResponseEntity<Object> deletar(@Valid @PathVariable Long id) throws CustomNotFoundException{
        pedidoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }


}
