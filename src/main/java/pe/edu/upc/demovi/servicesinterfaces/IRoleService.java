package pe.edu.upc.demovi.servicesinterfaces;

import pe.edu.upc.demovi.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public void insert(Role role);
    public List<Role> list();
    public Optional<Role> listId(Long id);
    public void delete(Long id);
    public void update(Role role);
}
