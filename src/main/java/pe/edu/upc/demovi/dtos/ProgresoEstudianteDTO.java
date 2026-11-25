package pe.edu.upc.demovi.dtos;

import java.util.List;

public class ProgresoEstudianteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Short grado;
    private String seccion;
    private List<EvaluacionResumenDTO> evaluaciones;

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

    public List<EvaluacionResumenDTO> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionResumenDTO> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
