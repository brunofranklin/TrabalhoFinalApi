package org.serratec.dto;

import javax.validation.constraints.NotNull;

import org.serratec.enums.EStatus;
import org.serratec.model.Cliente;
import org.serratec.model.Pedido;

public class PedidoDTO {

    private Long id;
    @NotNull
    private Cliente cliente;
    @NotNull
    private EStatus status; 

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.status = pedido.getStatus();
        this.cliente = pedido.getCliente();
    }

    public PedidoDTO(){
        
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EStatus getStatus() {
        return status;
    }
    public void setStatus(EStatus status) {
        this.status = status;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
