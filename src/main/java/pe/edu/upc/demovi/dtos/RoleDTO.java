package pe.edu.upc.demovi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RoleDTO {
    private Long id;
    @NotBlank(message = "El rol es obligatorio")
    private String rol;
    @NotNull(message = "El id del usuario es obligatorio")
    private Long idUser;
    private String descripcionRol;
    private LocalDateTime fechaCreacionRol;
    private LocalDateTime actualizadoEnRol;
    private Long idCreadoPor;
    private Long idActualizadoPor;

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public Long getIdCreadoPor() {
        return idCreadoPor;
    }

    public void setIdCreadoPor(Long idCreadoPor) {
        this.idCreadoPor = idCreadoPor;
    }

    public Long getIdActualizadoPor() {
        return idActualizadoPor;
    }

    public void setIdActualizadoPor(Long idActualizadoPor) {
        this.idActualizadoPor = idActualizadoPor;
    }
}
