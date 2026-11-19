package pe.edu.upc.demovi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "ejercicios")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "materia", length = 50, nullable = false)
    private String materia;

    @Column(name = "tema", length = 100, nullable = false)
    private String tema;

    @Column(name = "enunciado", columnDefinition = "text", nullable = false)
    private String enunciado;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contenido_json", columnDefinition = "jsonb")
    private String contenidoJson;

    @Column(name = "respuesta_correcta", columnDefinition = "text", nullable = false)
    private String respuestaCorrecta;

    @Column(name = "nivel_dificultad", nullable = false)
    private Short nivelDificultad;

    @Column(name = "generado_por_ia", nullable = false)
    private Boolean generadoPorIa;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private Users creadoPor;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Ejercicio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getContenidoJson() {
        return contenidoJson;
    }

    public void setContenidoJson(String contenidoJson) {
        this.contenidoJson = contenidoJson;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public Short getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(Short nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public Boolean getGeneradoPorIa() {
        return generadoPorIa;
    }

    public void setGeneradoPorIa(Boolean generadoPorIa) {
        this.generadoPorIa = generadoPorIa;
    }

    public Users getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Users creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
