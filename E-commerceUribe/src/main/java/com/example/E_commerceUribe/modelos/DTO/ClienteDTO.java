package com.example.E_commerceUribe.modelos.DTO;

import com.example.E_commerceUribe.ayudas.DepartamentoCliente;

public class ClienteDTO {

    private Integer id;
    private String direccion;
    private Double calificacion;
    private String referenciaPago;
    private DepartamentoCliente departamentoCliente;
    private String ciudad;
    private Integer usuarioId;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String direccion, Double calificacion, String referenciaPago, DepartamentoCliente departamentoCliente, String ciudad, Integer usuarioId) {
        this.id = id;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.referenciaPago = referenciaPago;
        this.departamentoCliente = departamentoCliente;
        this.ciudad = ciudad;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDireccion() { return direccion; } // Getter para 'direccion'
    public void setDireccion(String direccion) { this.direccion = direccion; } // Setter para 'direccion'
    public Double getCalificacion() { return calificacion; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }
    public String getReferenciaPago() { return referenciaPago; }
    public void setReferenciaPago(String referenciaPago) { this.referenciaPago = referenciaPago; }
    public DepartamentoCliente getDepartamentoCliente() { return departamentoCliente; }
    public void setDepartamentoCliente(DepartamentoCliente departamentoCliente) { this.departamentoCliente = departamentoCliente; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Integer getUsuarioId() { return usuarioId; } // Getter para 'usuarioId'
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; } // Setter para 'usuarioId'
}