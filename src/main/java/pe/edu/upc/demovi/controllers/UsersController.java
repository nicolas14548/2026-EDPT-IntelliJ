package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demovi.dtos.CantidadRolDTO;
import pe.edu.upc.demovi.dtos.UsersDTO;
import pe.edu.upc.demovi.dtos.UsersDTOInsert;
import pe.edu.upc.demovi.entities.Role;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsersController {
    private final IUsersService uS;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsersController(IUsersService uS, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.uS = uS;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping
    public ResponseEntity<List<UsersDTO>> listar() {
        List<UsersDTO> lista = uS.list()
                .stream()
                .map(users -> aDTO(users))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UsersDTO> registrar(@Valid @RequestBody UsersDTOInsert dto, Authentication auth) {
        Users apoderado = null;
        if (dto.getIdApoderado() != null) {
            apoderado = uS.listId(dto.getIdApoderado())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el apoderado con el id: " + dto.getIdApoderado()
                            )
                    );
        }
        Users docente = null;
        if (dto.getIdDocente() != null) {
            docente = uS.listId(dto.getIdDocente())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el docente con el id: " + dto.getIdDocente()
                            )
                    );
        }

        Users users = modelMapper.map(dto, Users.class);
        users.setId(null);
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));
        users.setCreadoPor(usuario);
        users.setActualizadoPor(null);
        users.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        Role rol = new Role();
        rol.setRol(dto.getRol());
        rol.setUser(users);
        rol.setFechaCreacionRol(LocalDateTime.now());
        rol.setCreadoPor(usuario);
        users.setRoles(new ArrayList<>(List.of(rol)));
        users.setApoderado(apoderado);
        users.setDocente(docente);
        users.setFechaRegistro(LocalDateTime.now());
        users.setUpdatedAt(null);
        uS.insert(users);

        UsersDTO responseDTO = aDTO(users);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(users.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> buscarPorId(@PathVariable Long id) {
        Users users = uS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con el id: " + id
                        )
                );
        UsersDTO dto = aDTO(users);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UsersDTO> buscarUsuarioAutenticado(Authentication auth) {
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));
        return ResponseEntity.ok(aDTO(usuario));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<UsersDTO> actualizar(@Valid @RequestBody UsersDTOInsert dto, Authentication auth) {
        Users existente = uS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con el id: " + dto.getId()
                        )
                );
        Users apoderado = null;
        if (dto.getIdApoderado() != null) {
            apoderado = uS.listId(dto.getIdApoderado())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el apoderado con el id: " + dto.getIdApoderado()
                            )
                    );
        }
        Users docente = null;
        if (dto.getIdDocente() != null) {
            docente = uS.listId(dto.getIdDocente())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el docente con el id: " + dto.getIdDocente()
                            )
                    );
        }

        Users users = modelMapper.map(dto, Users.class);
        users.setId(existente.getId());
        users.setCreadoPor(existente.getCreadoPor());
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));
        users.setActualizadoPor(usuario);
        users.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        Role rol = existente.getRoles()
                .stream()
                .filter(r -> r.getRol().equals(dto.getRol()))
                .findFirst()
                .orElseGet(() -> {
                    Role nuevo = new Role();
                    nuevo.setRol(dto.getRol());
                    nuevo.setFechaCreacionRol(LocalDateTime.now());
                    nuevo.setCreadoPor(usuario);
                    return nuevo;
                });
        rol.setUser(users);
        users.setRoles(new ArrayList<>(List.of(rol)));
        users.setApoderado(apoderado);
        users.setDocente(docente);
        users.setFechaRegistro(existente.getFechaRegistro());
        users.setUpdatedAt(LocalDateTime.now());
        uS.update(users);

        UsersDTO responseDTO = aDTO(users);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Users users = uS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un usuario con el id: " + id
                        )
                );
        uS.delete(users.getId());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cantidades")
    public ResponseEntity<List<CantidadRolDTO>> contarPorRol() {
        List<CantidadRolDTO> lista = uS.contarPorRol()
                .stream()
                .map(item -> {
                    CantidadRolDTO dto = new CantidadRolDTO();
                    dto.setNombreRol((String) item[0]);
                    dto.setCantidad(((Number) item[1]).longValue());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(lista);
    }

    private UsersDTO aDTO(Users users) {
        UsersDTO dto = modelMapper.map(users, UsersDTO.class);
        dto.setRol(users.getRoles().isEmpty() ? null : users.getRoles().get(0).getRol());
        return dto;
    }
}
