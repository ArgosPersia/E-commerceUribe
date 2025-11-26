package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;
import com.example.E_commerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // 👈 Cambié Optional por List, ya que el controlador busca una lista
import java.util.Optional;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByDepartamentoCliente(DepartamentoCliente departamentoCliente);

}