package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.EstadosUsuario;
import com.example.E_commerceUribe.ayudas.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table (name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name ="name", nullable = false, unique = false, length = 50)
    private String nombres;

    @Column (name = "email", nullable = false, unique = false, length = 25)
    private  String correo;

    @Column (name = "password", nullable = false, unique = false, length = 15)
    private  String contraseña;

    @Column (name = "status", nullable = false, unique = false)
    @Enumerated (EnumType.STRING) //-->
    private EstadosUsuario estado;

    @Column(name="dateOfBirth",nullable = true, unique = false)
    private LocalDate fechaNacimiento;

    @Column (name = "status", nullable = false, unique = false, length=10)
    @Enumerated (EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name="document",nullable = false, unique = true, length = 12)
    private String documento;

    //CREANDO UNA RELACION 1 A 1 CON EMPLEADO
    @OneToOne(mappedBy = "usuario")
    @JsonBackReference(value = "relacionempleadousuario")
    private Empleado empleado;
    public Usuario() {
    }

    public Usuario(Integer id, String nombres, String correo, String contraseña, EstadosUsuario estado, LocalDate fechaNacimiento, String documento, TipoDocumento tipoDocumento) {
        this.id = id;
        this.nombres = nombres;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public EstadosUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadosUsuario estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
