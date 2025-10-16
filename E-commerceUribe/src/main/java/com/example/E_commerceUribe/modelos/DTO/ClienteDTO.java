package com.example.E_commerceUribe.modelos.DTO;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;

public class ClienteDTO {

    private Integer id;
    private Double calificacion;
    private String referenciaPago;
    private DepartamentoCliente departamentoCliente;
    private String ciudad;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, Double calificacion, String referenciaPago, DepartamentoCliente departamentoCliente, String ciudad) {
        this.id = id;
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
