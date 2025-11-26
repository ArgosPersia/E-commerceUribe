package com.example.E_commerceUribe.modelos.DTO;

import java.time.LocalDate;

public class PedidoDTO {

    private Integer id;
    private Integer montoTotal;
    private LocalDate fechaEntrega;
    private Integer costoEnvio;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer montoTotal, LocalDate fechaEntrega, Integer costoEnvio) {
        this.montoTotal = montoTotal;
        this.fechaEntrega = fechaEntrega;
        this.costoEnvio = costoEnvio;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getMontoTotal() { return montoTotal; }
    public void setMontoTotal(Integer montoTotal) { this.montoTotal = montoTotal; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Integer getCostoEnvio() { return costoEnvio; }
    public void setCostoEnvio(Integer costoEnvio) { this.costoEnvio = costoEnvio; }
}