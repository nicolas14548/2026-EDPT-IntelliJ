package pe.edu.upc.demovi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EjercicioDTOInsert {
    private Long id;
    @NotBlank(message = "La materia es obligatoria")
    private String materia;
    @NotBlank(message = "El tema es obligatorio")
    private String tema;
    @NotBlank(message = "El enunciado es obligatorio")
    private String enunciado;
    private String contenidoJson;
    @NotBlank(message = "La respuesta correcta es obligatoria")
    private String respuestaCorrecta;
    @NotNull(message = "El nivel de dificultad es obligatorio")
    private Short nivelDificultad;
    @NotNull(message = "Debe indicar si el ejercicio fue generado por IA")
    private Boolean generadoPorIa;
    private Long idCreadoPor;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

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

    public Long getIdCreadoPor() {
        return idCreadoPor;
    }

    public void setIdCreadoPor(Long idCreadoPor) {
        this.idCreadoPor = idCreadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
