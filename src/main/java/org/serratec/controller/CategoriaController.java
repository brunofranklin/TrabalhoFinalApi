package org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.exception.CategoriaException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Categoria;
import org.serratec.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })  
    @GetMapping
    @ApiOperation(value = "Lista todos as categorias", notes = "Listagem de categorias")
    public ResponseEntity<Object> listar() throws CustomNoContentException{
        List<Categoria> categorias = categoriaService.listar();
        return ResponseEntity.ok(categorias);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/id/{id}")
    @ApiOperation(value = "Lista categoria por id", notes = "Listagem de categorias por id")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) throws CustomNotFoundException{
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);

    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404 , message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("/{nomeCategoria}")
    @ApiOperation(value = "Lista categoria por nome", notes = "Listagem de categorias por nome")
    public ResponseEntity<Object> buscarPorNome(@PathVariable String nomeCategoria)throws CustomNotFoundException{
        Categoria categoria = categoriaService.buscarPorNome(nomeCategoria.toUpperCase());
        return ResponseEntity.ok(categoria);

    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 202, message = "Criado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @PostMapping
    @ApiOperation(value = "Inserir categoria", notes = "Insere uma categoria")
    public ResponseEntity<Object> inserir(@Valid @RequestBody Categoria categoria)throws CategoriaException{
        categoria = categoriaService.inserir(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);

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
    @ApiOperation(value = "Atualizar categoria por id", notes = "Atualizar uma categoria por id")
    public ResponseEntity<Object> atualizar(@Valid @RequestBody Categoria categoria, @PathVariable Long id)
    throws CustomNotFoundException{
        
        categoria = categoriaService.atualizar(categoria, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 404 , message = "Não Encontrado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar categoria por id", notes = "Deleta uma categoria por id")
    public ResponseEntity<Object> deletar(@PathVariable Long id) throws CustomNotFoundException{
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
