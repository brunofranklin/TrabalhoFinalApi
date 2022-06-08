package org.serratec.service;

import org.serratec.dto.PedidoDTO;
import org.serratec.dto.PedidoInserirDTO;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.exception.PedidoException;
import org.serratec.model.Pedido;
import org.serratec.repository.ClienteRepository;
import org.serratec.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    

    public List<PedidoDTO> listar(){
        if(pedidoRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }else{
            List<Pedido> pedidos = pedidoRepository.findAll();
            return pedidos.stream().map(ped -> new PedidoDTO(ped)).collect(Collectors.toList());
        }
    }

    public Pedido buscarPorId(Long id){
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("Pedido com id '" + id + "' não foi encontrado"));
    }

    public PedidoInserirDTO inserir(PedidoInserirDTO pedidoInserirDTO){
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findByCpf(pedidoInserirDTO.getCpf()));
        pedido.setStatus(pedidoInserirDTO.getStatus());
        if(pedidoInserirDTO.getCpf() == null){
            throw new PedidoException("Você deve informar o cpf do cliente"
            + " o qual deseja relacionar com o pedido");
        }

        pedido = pedidoRepository.save(pedido);

        return new PedidoInserirDTO(pedido);
    }

    public PedidoDTO atualizar(PedidoInserirDTO pedidoInserirDTO, Long id){
        Pedido pedido = new Pedido();
        if(pedidoRepository.existsById(id)){
            pedido.setId(id);
            pedido.setCliente(clienteRepository.findByCpf(pedidoInserirDTO.getCpf()));
            pedido.setStatus(pedidoInserirDTO.getStatus());
            if(pedido.getCliente().getId() == null){
                throw new PedidoException("Você deve informar o id do cliente"
            + " o qual deseja relacionar com o pedido");
            }
            pedidoRepository.save(pedido);

            return new PedidoDTO(pedido);
        }   
		throw new CustomNotFoundException("Pedido com id '" + id + "' não foi encontrado");
    }

    public void deletar(Long id){
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
        }else{
            throw new CustomNotFoundException("Pedido com id '"+id+"' não encontrado");
        }
    }

}