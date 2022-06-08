package org.serratec.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.serratec.model.Pedido;
import org.serratec.model.PedidoProduto;
import org.serratec.model.Produto;

public class PedidoProdutoSelectDTO{

    private Pedido pedido;
    private Produto produto;
    private BigDecimal valorVenda;
    private double percDesconto;
    private Long quantidadeProduto;

    public PedidoProdutoSelectDTO(PedidoProduto pedidoProduto) {
        this.pedido = pedidoProduto.getPedido();
        this.produto = pedidoProduto.getProduto();
        this.valorVenda = calculoValorVenda(produto, pedidoProduto);
        this.percDesconto = pedidoProduto.getPercDesconto();
        this.quantidadeProduto = pedidoProduto.getQuantidadeProduto();
    }

    public BigDecimal calculoValorVenda(Produto produto, PedidoProduto pedidoProduto){
        BigDecimal valorTotal = produto.getValorUnitario()
        .multiply(BigDecimal.valueOf(pedidoProduto.getQuantidadeProduto()));
       
        this.valorVenda = (valorTotal).multiply(BigDecimal.valueOf(1 - pedidoProduto.getPercDesconto()/100));
        
        return valorVenda.setScale(2, RoundingMode.HALF_EVEN);
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

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
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
