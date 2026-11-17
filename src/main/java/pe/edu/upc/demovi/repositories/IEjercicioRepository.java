package pe.edu.upc.demovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demovi.entities.Ejercicio;

import java.util.List;

@Repository
public interface IEjercicioRepository extends JpaRepository<Ejercicio, Long> {
    public List<Ejercicio> findByMateriaContainingIgnoreCase(String materia);
}
