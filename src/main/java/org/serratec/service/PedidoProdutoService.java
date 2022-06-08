package org.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.dto.PedidoProdutoInserirDTO;
import org.serratec.dto.PedidoProdutoSelectDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.PedidoProdutoException;
//import org.serratec.exception.PedidoProdutoException;
import org.serratec.model.PedidoProduto;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.PedidoProdutoRepository;
import org.serratec.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProdutoService {
    
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Autowired 
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public List<PedidoProdutoSelectDTO> listar(){
        List<PedidoProduto> pedidoProdutos = pedidoProdutoRepository.findAll();
        if(!pedidoProdutos.isEmpty()){
            return pedidoProdutos.stream().map(pedProd -> new PedidoProdutoSelectDTO(pedProd))
            .collect(Collectors.toList());
        }else{
            throw new CustomNoContentException("null");
        }
    }

    public PedidoProduto buscarPorId(Long id){
        return pedidoProdutoRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("Pedido com id '" + id + "' não foi encontrado"));
    }

    public PedidoProdutoInserirDTO inserir(PedidoProdutoInserirDTO pedidoProdutoDTO){
        PedidoProduto pedidoProduto = new PedidoProduto();
        
        if(!produtoRepository.findById(pedidoProdutoDTO.getProduto().getId()).isPresent()){
            throw new PedidoProdutoException("Produto com id '"+pedidoProdutoDTO.getProduto().getId() +
            "' não existe");
        }
        if(!clienteRepository.findById(pedidoProdutoDTO.getPedido().getCliente().getId()).isPresent()){
            throw new PedidoProdutoException("Cliente com id '"+ pedidoProdutoDTO.getProduto().getId() +
            "' não existe");
        }
        
        pedidoProduto.setPedido(pedidoProdutoDTO.getPedido());
        pedidoProduto.setProduto(pedidoProdutoDTO.getProduto());
        pedidoProduto.setQuantidadeProduto(pedidoProdutoDTO.getQuantidadeProduto());
        pedidoProduto.setPercDesconto(pedidoProdutoDTO.getPercDesconto());
        pedidoProduto = pedidoProdutoRepository.save(pedidoProduto);
        
        return new PedidoProdutoInserirDTO(pedidoProduto);
    }

    public PedidoProduto atualizar(PedidoProduto pedidoProduto, Long id){
        if(pedidoProdutoRepository.existsById(id)){
            if(!produtoRepository.findById(pedidoProduto.getProduto().getId()).isPresent()){
                throw new PedidoProdutoException("Produto com id '"+pedidoProduto.getProduto().getId() +
                "' não existe");
            }
            if(!clienteRepository.findById(pedidoProduto.getPedido().getCliente().getId()).isPresent()){
                throw new PedidoProdutoException("Cliente com id '"+ pedidoProduto.getProduto().getId() +
                "' não existe");
            }
            
            pedidoProduto.setId(id);
            pedidoProduto.setPedido(pedidoProduto.getPedido());
            pedidoProduto.setProduto(pedidoProduto.getProduto());
            pedidoProduto.setQuantidadeProduto(pedidoProduto.getQuantidadeProduto());
            pedidoProduto.setPercDesconto(pedidoProduto.getPercDesconto());
            return pedidoProdutoRepository.save(pedidoProduto);
        }   
		throw new CustomNotFoundException("Pedido com id '" + id + "' não foi encontrado");
    }

    public void deletar(Long id){
        if(pedidoProdutoRepository.existsById(id)){
            pedidoProdutoRepository.deleteById(id);
        }else{
            throw new CustomNotFoundException("Pedido com id '"+id+"' não encontrado");
        }
    }

}
