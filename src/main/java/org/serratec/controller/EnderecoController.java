package org.serratec.controller;

import org.serratec.dto.EnderecoDTO;
import org.serratec.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retornado com sucesso"),
        @ApiResponse(code = 400, message = "Erro de requisição"),
        @ApiResponse(code = 401 , message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido acesso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro no servidor")
    })
    @GetMapping("{cep}")
    @ApiOperation(value = "Lista e cadastra endereço", notes = "Lista e cadastra endereço verificando no ViaCep")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep){
        EnderecoDTO enderecoDTO =  enderecoService.buscar(cep);
        if(enderecoDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enderecoDTO);
    }
    
}
