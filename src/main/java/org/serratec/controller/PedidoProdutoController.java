package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.dto.PedidoProdutoInserirDTO;
import org.serratec.dto.PedidoProdutoSelectDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.PedidoProdutoException;
import org.serratec.model.PedidoProduto;
import org.serratec.service.PedidoProdutoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoProdutoController {

    @Autowired
    private PedidoProdutoService pedidoProdutoService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping
    @ApiOperation(value = "Lista pedido_itens", notes = "Listagem de pedido_itens")
    public ResponseEntity<Object> listar() throws CustomNoContentException{
        List<PedidoProdutoSelectDTO> pedidoProdutosDTO = pedidoProdutoService.listar();
        return ResponseEntity.ok(pedidoProdutosDTO);
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
        PedidoProduto pedidoProduto = pedidoProdutoService.buscarPorId(id);
        return ResponseEntity.ok(pedidoProduto);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Cadastrar um pedido_item", notes = "Cadastra um pedido_item")
    public ResponseEntity<Object> inserir(@Valid @RequestBody PedidoProdutoInserirDTO pedidoProdutoDTO) 
    throws PedidoProdutoException{
        PedidoProdutoInserirDTO pedProdDTO = pedidoProdutoService.inserir(pedidoProdutoDTO);    
        return ResponseEntity.status(HttpStatus.CREATED).body(pedProdDTO);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PutMapping("{id}")
    @ApiOperation(value = "Atualizar peido_item", notes = "Atualiza um pedido_item")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> atualizar(@Valid @RequestBody PedidoProduto PedidoProduto, @PathVariable Long id) 
    throws CustomNotFoundException{
            
        PedidoProduto = pedidoProdutoService.atualizar(PedidoProduto, id);
        return ResponseEntity.ok().body(PedidoProduto);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar pedido_item", notes = "Deleta pedido_itens por id")
    public ResponseEntity<Object> deletar(@PathVariable Long id) throws CustomNotFoundException{
        pedidoProdutoService.deletar(id);
        return ResponseEntity.noContent().build();

    }
     
}
