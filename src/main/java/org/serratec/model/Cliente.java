package org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank
    @Column(name = "nome_cliente", nullable = false, length = 40)
    private String nome;
    
    @NotBlank
    @CPF(message = "CPF Inválido")
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;
    
    @NotBlank
    @Email(message = "E-mail inválido")
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;


    @Override
    public String toString() {
        return
        "\nemail: " + email + 
        "\nnome: " + nome ;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }  
    
    // public Pedido getPedidos() {
    //     return pedidos;
    // }
    // public void setPedidos(Pedido pedidos) {
    //     this.pedidos = pedidos;
    // }
    

}
