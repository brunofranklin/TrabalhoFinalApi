package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.dto.ProdutoDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.ProdutoException;
import org.serratec.model.Produto;
import org.serratec.dto.ProdutoInserirDTO;
import org.serratec.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })

    @GetMapping
    @ApiOperation(value = "Listar produtos", notes = "Listagem de produtos")
    public ResponseEntity<Object> listarTodos() throws CustomNoContentException{
        
        List<ProdutoDTO> produtos = produtoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Cadastrar produtos", notes = "Cadastra um produto")
    public ResponseEntity<Object> inserir(@Valid @RequestBody ProdutoInserirDTO produtoInserirDTO)
    throws ProdutoException{
        
        ProdutoDTO produtoDTO = produtoService.inserir(produtoInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);

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
    @ApiOperation(value = "Atualizar produto", notes = "Atualiza um produto por id")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id)
    throws ProdutoException, CustomNotFoundException{
        
        produto = produtoService.atualizar(produto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar produto", notes = "deleta um produto por id")
    public ResponseEntity<Object> deletar(@Valid @PathVariable Long id) throws CustomNotFoundException{
        
        produtoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }

}
