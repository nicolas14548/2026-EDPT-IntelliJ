package pe.edu.upc.demovi.dtos;

import java.time.LocalDateTime;

public class EvaluacionResumenDTO {
    private Long id;
    private String tipo;
    private String estado;
    private Short totalEjercicios;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer puntaje;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
