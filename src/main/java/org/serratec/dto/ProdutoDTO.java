package org.serratec.dto;

import java.math.BigDecimal;

import org.serratec.model.Categoria;
import org.serratec.model.Produto;

public class ProdutoDTO {
    
    private Long id;
    private String nome;
    private BigDecimal valorUnitario;
    private Categoria categoria;
    
    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = produto.getCategoria();
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
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
