package pe.edu.upc.demovi.servicesinterfaces;

import pe.edu.upc.demovi.entities.Users;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    public void insert(Users users);
    public List<Users> list();
    public Optional<Users> listId(Long id);
    public void delete(Long id);
    public void update(Users users);
    public List<Object[]> contarPorRol();
    public Optional<Users> findByEmail(String email);
    public List<Users> obtenerEstudiantesPorApoderado(Long idApoderado);
}