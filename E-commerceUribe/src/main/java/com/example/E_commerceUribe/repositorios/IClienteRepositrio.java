package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;
import com.example.E_commerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepositrio extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findbycliente (DepartamentoCliente departamentoCliente);
}

