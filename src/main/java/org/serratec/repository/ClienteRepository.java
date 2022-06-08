package org.serratec.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Transactional
    public Cliente findByEmail(String email);
    @Transactional
    public Cliente findByCpf(String cpf);
    @Transactional
    public Optional<Cliente> deleteByCpf(String cpf);
    @Transactional
    public Optional<Cliente> existsByCpf(String cpf);
}
