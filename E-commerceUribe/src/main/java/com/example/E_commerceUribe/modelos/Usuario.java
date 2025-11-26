package com.example.E_commerceUribe.modelos;

import com.example.E_commerceUribe.ayudas.EstadosUsuario;
import com.example.E_commerceUribe.ayudas.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore; // Importar esta!
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String correo;

    @Column(name = "password", nullable = false, length = 100)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadosUsuario estado;

    @Column(name="fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento", nullable = false, length = 15)
    private TipoDocumento tipoDocumento;

    @Column(name="documento", nullable = false, unique = true, length = 12)
    private String documento;


    @OneToOne(mappedBy = "usuario")
    @JsonIgnore // <-- CORRECCIÓN: Evita el ciclo de serialización
    private Cliente cliente;


    @OneToOne(mappedBy = "usuario")
    @JsonIgnore // <-- CORRECCIÓN: Evita el ciclo de serialización
    private Empleado empleado;

    public Usuario() {}

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



    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public EstadosUsuario getEstado() { return estado; }
    public void setEstado(EstadosUsuario estado) { this.estado = estado; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(TipoDocumento tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    // Getters y Setters de relaciones
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
}