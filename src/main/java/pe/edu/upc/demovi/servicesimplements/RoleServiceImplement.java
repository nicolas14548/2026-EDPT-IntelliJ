package pe.edu.upc.demovi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demovi.entities.Role;
import pe.edu.upc.demovi.repositories.IRoleRepository;
import pe.edu.upc.demovi.servicesinterfaces.IRoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplement implements IRoleService {
    private final IRoleRepository r;

    public RoleServiceImplement(IRoleRepository r) {
        this.r = r;
    }

    @Override
    public void insert(Role role) {
        r.save(role);
    }

    @Override
    public List<Role> list() {
        return r.findAll();
    }

    @Override
    public Optional<Role> listId(Long id) {
        return r.findById(id);
    }

    @Override
    public void delete(Long id) {
        r.deleteById(id);
    }

    @Override
    public void update(Role role) {
        r.save(role);
    }
}
