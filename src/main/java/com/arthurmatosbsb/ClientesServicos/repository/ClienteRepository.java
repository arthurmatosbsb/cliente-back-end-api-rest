package com.arthurmatosbsb.ClientesServicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurmatosbsb.ClientesServicos.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
