package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.CargoEmpleado;
import com.example.E_commerceUribe.ayudas.SedeEmpleado;
import jakarta.persistence.*;

@Entity
@Table(name="empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name="cargoEmpleado",nullable = false, unique = false)
    private CargoEmpleado cargoEmpleado;

    @Column(name="salario",nullable = false, unique = false)
    private Integer salario;

    @Enumerated(EnumType.STRING)
    @Column(name="sedeEmpleado",nullable = false, unique = false)
    private SedeEmpleado sedeEmpleado;

    @OneToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Empleado() {
    }


    public Empleado(CargoEmpleado cargoEmpleado, Integer salario, SedeEmpleado sedeEmpleado, Usuario usuario) {
        this.cargoEmpleado = cargoEmpleado;
        this.salario = salario;
        this.sedeEmpleado = sedeEmpleado;
        this.usuario = usuario;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public CargoEmpleado getCargoEmpleado() { return cargoEmpleado; }
    public void setCargoEmpleado(CargoEmpleado cargoEmpleado) { this.cargoEmpleado = cargoEmpleado; }
    public Integer getSalario() { return salario; }
    public void setSalario(Integer salario) { this.salario = salario; }
    public SedeEmpleado getSedeEmpleado() { return sedeEmpleado; }
    public void setSedeEmpleado(SedeEmpleado sedeEmpleado) { this.sedeEmpleado = sedeEmpleado; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}