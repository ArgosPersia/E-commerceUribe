package com.example.E_commerceUribe.modelos.DTO;

import com.example.E_commerceUribe.ayudas.CargoEmpleado;
import com.example.E_commerceUribe.ayudas.SedeEmpleado;

public class EmpleadoDTO {

    private Integer id;
    private CargoEmpleado cargoEmpleado;
    private Integer salario;
    private SedeEmpleado sedeEmpleado;
    private Integer usuarioId;

    public EmpleadoDTO() {
    }


    public EmpleadoDTO(CargoEmpleado cargoEmpleado, Integer salario, SedeEmpleado sedeEmpleado, Integer usuarioId) {
        this.cargoEmpleado = cargoEmpleado;
        this.salario = salario;
        this.sedeEmpleado = sedeEmpleado;
        this.usuarioId = usuarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public CargoEmpleado getCargoEmpleado() { return cargoEmpleado; }
    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) { this.cargoEmpleado = cargoEmpleado; }

    public Integer getSalario() { return salario; }
    public void setSalario(Integer salario) { this.salario = salario; }

    public SedeEmpleado getSedeEmpleado() { return sedeEmpleado; }
    public void setSedeEmpleado(SedeEmpleado sedeEmpleado) { this.sedeEmpleado = sedeEmpleado; }
}