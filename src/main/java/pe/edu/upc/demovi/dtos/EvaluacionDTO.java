package pe.edu.upc.demovi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EvaluacionDTO {
    private Long id;
    @NotNull(message = "El id del estudiante es obligatorio")
    private Long idEstudiante;
    @NotBlank(message = "El tipo de evaluación es obligatorio")
    private String tipo;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    @NotNull(message = "El total de ejercicios es obligatorio")
    private Short totalEjercicios;
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer puntaje;
    private String respuestas;
    private Long idCreadoPor;
    private Long idActualizadoPor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Short getTotalEjercicios() {
        return totalEjercicios;
    }

    public void setTotalEjercicios(Short totalEjercicios) {
        this.totalEjercicios = totalEjercicios;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
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
