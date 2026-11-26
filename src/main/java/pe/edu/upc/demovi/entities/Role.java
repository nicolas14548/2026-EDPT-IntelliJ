package pe.edu.upc.demovi.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "rol"})
        }
)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "descripcion_rol", length = 200)
    private String descripcionRol;

    @Column(name = "fecha_creacion_rol")
    private LocalDateTime fechaCreacionRol;

    @Column(name = "actualizado_en_rol")
    private LocalDateTime actualizadoEnRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Users creadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
