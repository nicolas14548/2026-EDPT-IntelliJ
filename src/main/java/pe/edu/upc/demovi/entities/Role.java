package pe.edu.upc.demovi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rol", length = 30, nullable = false, unique = true)
    private String nombreRol;

    @Column(name = "descripcion_rol", length = 200)
    private String descripcionRol;

    @Column(name = "fecha_creacion_rol")
    private LocalDateTime fechaCreacionRol;

    @Column(name = "actualizado_en_rol")
    private LocalDateTime actualizadoEnRol;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private Users creadoPor;

    @ManyToOne
    @JoinColumn(name = "actualizado_por")
    private Users actualizadoPor;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public LocalDateTime getFechaCreacionRol() {
        return fechaCreacionRol;
    }

    public void setFechaCreacionRol(LocalDateTime fechaCreacionRol) {
        this.fechaCreacionRol = fechaCreacionRol;
    }

    public LocalDateTime getActualizadoEnRol() {
        return actualizadoEnRol;
    }

    public void setActualizadoEnRol(LocalDateTime actualizadoEnRol) {
        this.actualizadoEnRol = actualizadoEnRol;
    }

    public Users getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Users creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Users getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(Users actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }
}