package pe.edu.upc.demovi.dtos;

import java.time.LocalDateTime;

public class UsersDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private Long idApoderado;
    private Long idDocente;
    private Short grado;
    private String seccion;
    private Boolean enabled;
    private LocalDateTime fechaRegistro;
    private LocalDateTime updatedAt;
    private Long idCreadoPor;
    private Long idActualizadoPor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Long idApoderado) {
        this.idApoderado = idApoderado;
    }

    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public Short getGrado() {
        return grado;
    }

    public void setGrado(Short grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
