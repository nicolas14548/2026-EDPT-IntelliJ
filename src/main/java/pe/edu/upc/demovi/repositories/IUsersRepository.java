package pe.edu.upc.demovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demovi.entities.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    @Query(value = "select r.nombre_rol, count(u.id)\n" +
            " from roles r left join usuarios u\n" +
            " on u.rol_id=r.id\n" +
            " group by r.id, r.nombre_rol", nativeQuery = true)
    List<Object[]> contarUsuariosPorRol();
}
