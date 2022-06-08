package org.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.dto.ProdutoDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.ProdutoException;
import org.serratec.dto.ProdutoInserirDTO;
import org.serratec.model.Produto;
import org.serratec.repository.CategoriaRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoDTO> listar(){
        if(produtoRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }
        List<Produto> produtos = produtoRepository.findAll();     
        return produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());
    }
    
    public ProdutoDTO inserir(ProdutoInserirDTO produtoInserirDTO){
        Produto produto = new Produto();
        produto.setNome(produtoInserirDTO.getNome().toUpperCase());
        produto.setValorUnitario(produtoInserirDTO.getValorUnitario());
        produto.setCategoria(produtoInserirDTO.getCategoria());
        
        if(produtoInserirDTO.getCategoria().getId() == null){
            throw new ProdutoException("Você deve informar o id da categoria"
            + " a qual deseja relacionar com o produto");
        }
        categoriaRepository.findById(produtoInserirDTO.getCategoria().getId())
        .orElseThrow(() -> new ProdutoException("Categoria com id '" + produtoInserirDTO.getCategoria().getId()
        + "' não encontrada"));

        produto = produtoRepository.save(produto);

        return new ProdutoDTO(produto);
    }

    public Produto atualizar(Produto produto, Long id){
        if(produtoRepository.existsById(id)){
            produto.setId(id);
            produto.setNome(produto.getNome().toUpperCase());
            produto.setValorUnitario(produto.getValorUnitario());
            produto.setCategoria(produto.getCategoria());

            if(produto.getCategoria().getId() == null){
                throw new ProdutoException("Você deve informar o id da categoria"
                + " a qual deseja relacionar com o produto");
            }
            categoriaRepository.findById(produto.getCategoria().getId())
            .orElseThrow(() -> new ProdutoException("Categoria com id '" + produto.getCategoria().getId()
            + "' não encontrada"));

            return produtoRepository.save(produto);
        }   
		throw new CustomNotFoundException("Produto com id '"+id+"' não encontrado");
    }

    public void deletar(Long id){
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
        }else{
            throw new CustomNotFoundException("Produto com id '"+id+"' não encontrado");
        }
    }  
}
