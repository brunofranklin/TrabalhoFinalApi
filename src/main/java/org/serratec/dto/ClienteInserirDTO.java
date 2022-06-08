package org.serratec.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.model.Cliente;

public class ClienteInserirDTO {
    
    @NotBlank
    @Size(max = 40, min = 2)
    private String nome;
   
    @NotBlank
    @CPF(message = "CPF Inválido")
    private String cpf;

    @NotBlank
    @Size(max = 40)
    @Email(message = "E-mail inválido")
    private String email;
    
    @NotNull
    private String cep;
    
    public ClienteInserirDTO(){

    }

    public ClienteInserirDTO(Cliente cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.cep = cliente.getEndereco().getCep();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
}
