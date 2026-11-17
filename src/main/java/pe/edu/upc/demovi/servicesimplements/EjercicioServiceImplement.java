package pe.edu.upc.demovi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demovi.entities.Ejercicio;
import pe.edu.upc.demovi.repositories.IEjercicioRepository;
import pe.edu.upc.demovi.servicesinterfaces.IEjercicioService;

import java.util.List;
import java.util.Optional;

@Service
public class EjercicioServiceImplement implements IEjercicioService {
    private final IEjercicioRepository r;

    public EjercicioServiceImplement(IEjercicioRepository r) {
        this.r = r;
    }

    @Override
    public void insert(Ejercicio ejercicio) {
        r.save(ejercicio);
    }

    @Override
    public List<Ejercicio> list() {
        return r.findAll();
    }

    @Override
    public Optional<Ejercicio> listId(Long id) {
        return r.findById(id);
    }

    @Override
    public void delete(Long id) {
        r.deleteById(id);
    }

    @Override
    public void update(Ejercicio ejercicio) {
        r.save(ejercicio);
    }

    @Override
    public List<Ejercicio> obtenerPorMateria(String materia) {
        return r.findByMateriaContainingIgnoreCase(materia);
    }
}
