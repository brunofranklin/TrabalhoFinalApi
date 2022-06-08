package org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.serratec.enums.EStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Pedido{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private EStatus status;
    
    @NotNull
    @OneToOne
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<PedidoProduto> pedidoProduto;

    @Override
    public String toString() {
        return "Pedido: " + 
        "Status: " + status;
        
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

    public List<PedidoProduto> getPedidoProduto() {
        return pedidoProduto;
    }

    public void setPedidoProduto(List<PedidoProduto> pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }
}
