package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demovi.dtos.EjercicioDTO;
import pe.edu.upc.demovi.dtos.EjercicioDTOInsert;
import pe.edu.upc.demovi.entities.Ejercicio;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demovi.servicesinterfaces.IEjercicioService;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ejercicios")
public class EjercicioController {
    private final IEjercicioService eS;
    private final IUsersService uS;
    private final ModelMapper modelMapper;

    public EjercicioController(IEjercicioService eS, IUsersService uS, ModelMapper modelMapper) {
        this.eS = eS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE', 'ESTUDIANTE')")
    @GetMapping
    public ResponseEntity<List<EjercicioDTO>> listar() {
        List<EjercicioDTO> lista = eS.list()
                .stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @PostMapping
    public ResponseEntity<EjercicioDTOInsert> registrar(@Valid @RequestBody EjercicioDTOInsert dto, Authentication auth) {

        Ejercicio ejercicio = modelMapper.map(dto, Ejercicio.class);
        ejercicio.setId(null);
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));
        ejercicio.setCreadoPor(usuario);
        ejercicio.setCreatedAt(LocalDateTime.now());
        ejercicio.setUpdatedAt(null);
        ejercicio.setActualizadoPor(null);
        eS.insert(ejercicio);

        EjercicioDTOInsert responseDTO = modelMapper.map(ejercicio, EjercicioDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ejercicio.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<EjercicioDTOInsert> buscarPorId(@PathVariable Long id) {
        Ejercicio ejercicio = eS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un ejercicio con el id: " + id
                        )
                );
        EjercicioDTOInsert dto = modelMapper.map(ejercicio, EjercicioDTOInsert.class);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @PutMapping
    public ResponseEntity<EjercicioDTOInsert> actualizar(@Valid @RequestBody EjercicioDTOInsert dto, Authentication auth) {

        Ejercicio existente = eS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un ejercicio con el id: " + dto.getId()
                        )
                );
        Ejercicio ejercicio = modelMapper.map(dto, Ejercicio.class);
        ejercicio.setId(existente.getId());
        ejercicio.setCreadoPor(existente.getCreadoPor());
        ejercicio.setCreatedAt(existente.getCreatedAt());
        ejercicio.setUpdatedAt(LocalDateTime.now());
        Users usuario = uS.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: " + auth.getName()));
        ejercicio.setActualizadoPor(usuario);
        eS.update(ejercicio);

        EjercicioDTOInsert responseDTO = modelMapper.map(ejercicio, EjercicioDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Ejercicio ejercicio = eS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un ejercicio con el id: " + id
                        )
                );
        eS.delete(ejercicio.getId());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE', 'ESTUDIANTE')")
    @GetMapping("/materias")
    public ResponseEntity<List<EjercicioDTO>> buscarPorMateria(@RequestParam String materia) {
        List<EjercicioDTO> lista = eS.obtenerPorMateria(materia)
                .stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
