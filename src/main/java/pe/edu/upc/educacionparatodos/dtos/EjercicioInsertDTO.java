package pe.edu.upc.educacionparatodos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EjercicioInsertDTO {

    @NotBlank(message = "El tema es obligatorio")
    private String tema;

    @NotBlank(message = "El enunciado es obligatorio")
    private String enunciado;

    private String contenidoJson;

    @NotBlank(message = "La respuesta correcta es obligatoria")
    private String respuestaCorrecta;

    private Integer nivelDificultad;

    private Boolean generadoPorIa;

    private String estado;

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

    public Integer getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(Integer nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public Boolean getGeneradoPorIa() {
        return generadoPorIa;
    }

    public void setGeneradoPorIa(Boolean generadoPorIa) {
        this.generadoPorIa = generadoPorIa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
