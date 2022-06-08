package org.serratec.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.serratec.model.Pedido;
import org.serratec.model.PedidoProduto;
import org.serratec.model.Produto;


public class PedidoProdutoInserirDTO {
    
    @NotNull
    private Pedido pedido;
    
    @NotNull
    private Produto produto;
    
    @NotNull
    @DecimalMin(value = "1")
    @Digits(integer=10, fraction=0)
    private Long quantidadeProduto;
    
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "75")
    @Digits(integer=2, fraction=0)
    private double percDesconto;

    public PedidoProdutoInserirDTO(PedidoProduto pedidoProduto) {
        this.pedido = pedidoProduto.getPedido();
        this.produto = pedidoProduto.getProduto();
        this.quantidadeProduto = pedidoProduto.getQuantidadeProduto();
        this.percDesconto = pedidoProduto.getPercDesconto();
    }

    public PedidoProdutoInserirDTO(){
        
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Long quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public double getPercDesconto() {
        return percDesconto;
    }

    public void setPercDesconto(double percDesconto) {
        this.percDesconto = percDesconto;
    }
}
