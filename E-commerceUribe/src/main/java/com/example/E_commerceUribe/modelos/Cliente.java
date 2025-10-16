package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;
import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "direccion", nullable = false, unique = false, length = 50)
    private String direccion;

    @Column (name = "calificacion", nullable = false, unique = false, length = 50)
    private Double calificacion;

    @Column (name = "referenciaPago", nullable = false, unique = false, length = 50)
    private String referenciaPago;

    @Column (name = "departamentoCliente", nullable = false, unique = false)
    @Enumerated (EnumType.STRING)
    private DepartamentoCliente departamentoCliente;

    @Column (name = "ciudad", nullable = false, unique = false, length = 50)
    private String ciudad;

    public Cliente() {
    }

    public Cliente(Integer id, String direccion, Double calificacion, String referenciaPago, DepartamentoCliente departamentoCliente, String ciudad) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamentoCliente = departamentoCliente;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getReferenciaPago() {
        return referenciaPago;
    }

    public void setReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
    }

    public DepartamentoCliente getDepartamentoCliente() {
        return departamentoCliente;
    }

    public void setDepartamentoCliente(DepartamentoCliente departamentoCliente) {
        this.departamentoCliente = departamentoCliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
