package org.serratec.repository;

import org.serratec.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    public Categoria findByNome(String nome);
}
