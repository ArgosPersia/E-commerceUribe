package com.example.E_commerceUribe.repositorios;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;
import com.example.E_commerceUribe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; // 👈 Cambié Optional por List, ya que el controlador busca una lista
import java.util.Optional;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {

    // SOLUCIÓN: Usar 'findBy' seguido del nombre exacto de la propiedad (DepartamentoCliente)
    List<Cliente> findByDepartamentoCliente(DepartamentoCliente departamentoCliente);

    // Si tu servicio realmente necesita un Optional:
    // Optional<Cliente> findFirstByDepartamentoCliente(DepartamentoCliente departamentoCliente);

    // **NOTA:** Es más común que la búsqueda por un campo que no es ID devuelva una lista,
    // ya que puede haber varios clientes en el mismo departamento.
}