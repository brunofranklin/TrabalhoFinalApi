package org.serratec.service;

import java.util.List;

import org.serratec.exception.CategoriaException;
import org.serratec.exception.CustomNoContentException;
import org.serratec.exception.CustomNotFoundException;
import org.serratec.model.Categoria;
import org.serratec.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    //LISTA TODAS AS CATEGORIAS CADASTRADAS
    public List<Categoria> listar(){
        if(categoriaRepository.findAll().isEmpty()){
            throw new CustomNoContentException("");
        }
        return categoriaRepository.findAll();
    }

    //BUSCA CATEGORIA POR ID
    public Categoria buscarPorId(Long id){
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new CustomNotFoundException("Categoria com id '" + id + "' não foi encontrada"));
    }

    //BUSCA CATEGORIA POR NOME
    public Categoria buscarPorNome(String nomeCategoria){
        if(categoriaRepository.findByNome(nomeCategoria) == null){
            throw new CategoriaException("Categoria '" + nomeCategoria + "' não foi encontrada");
        }
        return categoriaRepository.findByNome(nomeCategoria);
    }

    //CADASTRA CATEGORIAS
    public Categoria inserir(Categoria categoria){
        if(categoriaRepository.findByNome(categoria.getNome().toUpperCase()) != null){
            throw new CategoriaException("Categoria '" + categoria.getNome() + "' já está cadastrada");
        }
        categoria.setNome(categoria.getNome().toUpperCase());
        return categoriaRepository.save(categoria);
    }

    //ATUALIZA CATEGORIA
    public Categoria atualizar(Categoria categoria, Long id){
		if(categoriaRepository.existsById(id)){
            categoria.setId(id);
            categoria.setNome(categoria.getNome().toUpperCase());
            return categoriaRepository.save(categoria);
        }   
		throw new CustomNotFoundException("Categoria com id '" + id + "' não foi encontrada");
    }

    //DELETA CATEGORIA
    public void deletar(Long id){    
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
        }
        else{
            throw new CustomNotFoundException("Categoria com id '" + id + "' não foi encontrada");
        }
    }
    
}
