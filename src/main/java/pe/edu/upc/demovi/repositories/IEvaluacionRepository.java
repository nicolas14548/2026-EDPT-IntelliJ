package pe.edu.upc.demovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demovi.entities.Evaluacion;

import java.util.List;

@Repository
public interface IEvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByEstudianteIdOrderByFechaInicioDesc(Long idEstudiante);

    @Query(value = "select u.nombre, count(e.id)\n" +
            " from usuarios u inner join roles r\n" +
            " on r.id=u.rol_id\n" +
            " left join evaluaciones e\n" +
            " on e.estudiante_id=u.id\n" +
            " where r.nombre_rol='ESTUDIANTE'\n" +
            " group by u.id, u.nombre", nativeQuery = true)
    List<Object[]> contarEvaluacionesPorEstudiante();
}
