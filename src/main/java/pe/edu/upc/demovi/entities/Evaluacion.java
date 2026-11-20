package pe.edu.upc.demovi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Users estudiante;

    @Column(name = "tipo", length = 30, nullable = false)
    private String tipo;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "total_ejercicios", nullable = false)
    private Short totalEjercicios;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "puntaje")
    private Integer puntaje;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "respuestas", columnDefinition = "jsonb")
    private String respuestas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Users creadoPor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actualizado_por")
    private Users actualizadoPor;

    public Evaluacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Users estudiante) {
        this.estudiante = estudiante;
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
