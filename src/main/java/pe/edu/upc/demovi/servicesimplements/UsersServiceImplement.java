package pe.edu.upc.demovi.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.repositories.IUsersRepository;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImplement implements IUsersService {
    private final IUsersRepository r;

    public UsersServiceImplement(IUsersRepository r) {
        this.r = r;
    }

    @Override
    public void insert(Users users) {
        r.save(users);
    }

    @Override
    public List<Users> list() {
        return r.findAll();
    }

    @Override
    public Optional<Users> listId(Long id) {
        return r.findById(id);
    }

    @Override
    public void delete(Long id) {
        r.deleteById(id);
    }

    @Override
    public void update(Users users) {
        r.save(users);
    }

    @Override
    public List<Object[]> contarPorRol() {
        return r.contarUsuariosPorRol();
    }
}
