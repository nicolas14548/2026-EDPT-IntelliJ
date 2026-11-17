package pe.edu.upc.demovi.servicesinterfaces;

import pe.edu.upc.demovi.entities.Evaluacion;

import java.util.List;
import java.util.Optional;

public interface IEvaluacionService {
    public void insert(Evaluacion evaluacion);
    public List<Evaluacion> list();
    public Optional<Evaluacion> listId(Long id);
    public void delete(Long id);
    public void update(Evaluacion evaluacion);
    public List<Evaluacion> obtenerPorEstudiante(Long idEstudiante);
    public List<Object[]> contarPorEstudiante();
}
