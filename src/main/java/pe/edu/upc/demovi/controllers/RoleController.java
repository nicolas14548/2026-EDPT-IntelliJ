package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
public class RoleController {
    private final IRoleService rS;
    private final IUsersService uS;
    private final ModelMapper modelMapper;

    public RoleController(IRoleService rS, IUsersService uS, ModelMapper modelMapper) {
        this.rS = rS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listar() {
        List<RoleDTO> lista = rS.list()
                .stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> registrar(@Valid @RequestBody RoleDTO dto, Authentication auth) {
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));

        Role role = modelMapper.map(dto, Role.class);
        role.setId(null);
        role.setFechaCreacionRol(LocalDateTime.now());
        role.setActualizadoEnRol(null);
        role.setCreadoPor(usuario);
        role.setActualizadoPor(null);
        rS.insert(role);

        RoleDTO responseDTO = modelMapper.map(role, RoleDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> buscarPorId(@PathVariable Long id) {
        Role role = rS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con el id: " + id
                        )
                );
        RoleDTO dto = modelMapper.map(role, RoleDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> actualizar(@Valid @RequestBody RoleDTO dto, Authentication auth) {
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));

        Role existente = rS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con el id: " + dto.getId()
                        )
                );
        Role role = modelMapper.map(dto, Role.class);
        role.setId(existente.getId());
        role.setFechaCreacionRol(existente.getFechaCreacionRol());
        role.setActualizadoEnRol(LocalDateTime.now());
        role.setCreadoPor(existente.getCreadoPor());
        role.setActualizadoPor(usuario);
        rS.update(role);

        RoleDTO responseDTO = modelMapper.map(role, RoleDTO.class);
        return ResponseEntity.ok(responseDTO);
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
}
