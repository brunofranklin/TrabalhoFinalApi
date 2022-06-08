package org.serratec.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.enums.EStatus;
import org.serratec.model.Pedido;

public class PedidoInserirDTO {
    
    @CPF
    @NotNull
    private String cpf;
    @NotNull
    private EStatus status;
    
    public PedidoInserirDTO(Pedido pedido) {
        this.cpf = pedido.getCliente().getCpf();
        this.status = pedido.getStatus();
    }
 
    public PedidoInserirDTO() {
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public EStatus getStatus() {
        return status;
    }
    public void setStatus(EStatus status) {
        this.status = status;
    } 

    

}
