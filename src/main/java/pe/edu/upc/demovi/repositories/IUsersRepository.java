package pe.edu.upc.demovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demovi.entities.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    @Query("""
            select distinct u from Users u join u.roles r
            where u.apoderado.id = :idApoderado
              and u.enabled = true
              and r.rol = 'ROLE_ESTUDIANTE'
            order by u.nombre asc
            """)
    List<Users> findEstudiantesActivosPorApoderado(@Param("idApoderado") Long idApoderado);

    @Query(value = "select r.rol, count(r.user_id)\n" +
            " from roles r\n" +
            " group by r.rol", nativeQuery = true)
    List<Object[]> contarUsuariosPorRol();
}
