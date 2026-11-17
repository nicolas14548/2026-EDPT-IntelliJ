package pe.edu.upc.demovi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demovi.entities.Evaluacion;
import pe.edu.upc.demovi.repositories.IEvaluacionRepository;
import pe.edu.upc.demovi.servicesinterfaces.IEvaluacionService;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionServiceImplement implements IEvaluacionService {
    private final IEvaluacionRepository r;

    public EvaluacionServiceImplement(IEvaluacionRepository r) {
        this.r = r;
    }

    @Override
    public void insert(Evaluacion evaluacion) {
        r.save(evaluacion);
    }

    @Override
    public List<Evaluacion> list() {
        return r.findAll();
    }

    @Override
    public Optional<Evaluacion> listId(Long id) {
        return r.findById(id);
    }

    @Override
    public void delete(Long id) {
        r.deleteById(id);
    }

    @Override
    public void update(Evaluacion evaluacion) {
        r.save(evaluacion);
    }

    @Override
    public List<Evaluacion> obtenerPorEstudiante(Long idEstudiante) {
        return r.findByEstudianteIdOrderByFechaInicioDesc(idEstudiante);
    }

    @Override
    public List<Object[]> contarPorEstudiante() {
        return r.contarEvaluacionesPorEstudiante();
    }
}
