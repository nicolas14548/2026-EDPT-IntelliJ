package pe.edu.upc.demovi.servicesinterfaces;

import pe.edu.upc.demovi.entities.Ejercicio;

import java.util.List;
import java.util.Optional;

public interface IEjercicioService {
    public void insert(Ejercicio ejercicio);
    public List<Ejercicio> list();
    public Optional<Ejercicio> listId(Long id);
    public void delete(Long id);
    public void update(Ejercicio ejercicio);
    public List<Ejercicio> obtenerPorMateria(String materia);
}
