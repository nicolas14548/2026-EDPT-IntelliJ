package pe.edu.upc.educacionparatodos.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.educacionparatodos.entities.Ejercicio;
import pe.edu.upc.educacionparatodos.exceptions.ResourceNotFoundException;
import pe.edu.upc.educacionparatodos.repositories.IEjercicioRepository;
import pe.edu.upc.educacionparatodos.servicesinterfaces.IEjercicioService;

import java.util.List;

@Service
public class EjercicioServiceImplement implements IEjercicioService {

    private final IEjercicioRepository ejercicioRepository;

    public EjercicioServiceImplement(IEjercicioRepository ejercicioRepository) {
        this.ejercicioRepository = ejercicioRepository;
    }

    @Override
    public List<Ejercicio> list() {
        return ejercicioRepository.findAll();
    }

    @Override
    public Ejercicio findById(Long id) {
        return ejercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un ejercicio con el id: " + id
                ));
    }

    @Override
    public Ejercicio insert(Ejercicio e) {
        return ejercicioRepository.save(e);
    }

    @Override
    public Ejercicio update(Long id, Ejercicio e) {
        Ejercicio existente = findById(id);

        existente.setTema(e.getTema());
        existente.setEnunciado(e.getEnunciado());
        existente.setContenidoJson(e.getContenidoJson());
        existente.setRespuestaCorrecta(e.getRespuestaCorrecta());
        existente.setNivelDificultad(e.getNivelDificultad());
        existente.setGeneradoPorIa(e.getGeneradoPorIa());
        existente.setEstado(e.getEstado());

        return ejercicioRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        Ejercicio ejercicio = findById(id);
        ejercicioRepository.deleteById(ejercicio.getId());
    }

}
