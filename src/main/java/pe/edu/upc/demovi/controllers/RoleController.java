package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demovi.dtos.RoleDTO;
import pe.edu.upc.demovi.entities.Role;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demovi.servicesinterfaces.IRoleService;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final IRoleService rS;
    private final IUsersService uS;

    public RoleController(IRoleService rS, IUsersService uS) {
        this.rS = rS;
        this.uS = uS;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listar() {
        List<RoleDTO> lista = rS.list()
                .stream()
                .map(role -> aDTO(role))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> registrar(@Valid @RequestBody RoleDTO dto, Authentication auth) {
        Users user = uS.listId(dto.getIdUser())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el usuario con el id: " + dto.getIdUser()
                        )
                );

        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));

        Role role = new Role();
        role.setRol(dto.getRol());
        role.setUser(user);
        role.setDescripcionRol(dto.getDescripcionRol());
        role.setFechaCreacionRol(LocalDateTime.now());
        role.setActualizadoEnRol(null);
        role.setCreadoPor(usuario);
        role.setActualizadoPor(null);

        rS.insert(role);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(aDTO(role));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> buscarPorId(@PathVariable Long id) {
        Role role = rS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con el id: " + id
                        )
                );

        return ResponseEntity.ok(aDTO(role));
    }

    @PutMapping
    public ResponseEntity<RoleDTO> actualizar(@Valid @RequestBody RoleDTO dto, Authentication auth) {
        Role role = rS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con el id: " + dto.getId()
                        )
                );

        Users user = uS.listId(dto.getIdUser())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el usuario con el id: " + dto.getIdUser()
                        )
                );

        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));

        role.setRol(dto.getRol());
        role.setUser(user);
        role.setDescripcionRol(dto.getDescripcionRol());
        role.setActualizadoEnRol(LocalDateTime.now());
        role.setActualizadoPor(usuario);

        rS.update(role);

        return ResponseEntity.ok(aDTO(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Role role = rS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con el id: " + id
                        )
                );

        rS.delete(role.getId());

        return ResponseEntity.noContent().build();
    }

    private RoleDTO aDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setRol(role.getRol());
        dto.setIdUser(role.getUser().getId());
        dto.setDescripcionRol(role.getDescripcionRol());
        dto.setFechaCreacionRol(role.getFechaCreacionRol());
        dto.setActualizadoEnRol(role.getActualizadoEnRol());
        dto.setIdCreadoPor(role.getCreadoPor() != null ? role.getCreadoPor().getId() : null);
        dto.setIdActualizadoPor(role.getActualizadoPor() != null ? role.getActualizadoPor().getId() : null);
        return dto;
    }
}
