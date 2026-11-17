package pe.edu.upc.demovi.servicesimplements;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.repositories.IUsersRepository;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final IUsersRepository usersRepository;

    public JwtUserDetailsService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // El "username" del login es el email del usuario
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado: " + username
                        )
                );

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getRol().getNombreRol())
        );

        return User.builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .authorities(authorities)
                .disabled(!"ACTIVO".equals(user.getEstado()))
                .build();
    }
}
