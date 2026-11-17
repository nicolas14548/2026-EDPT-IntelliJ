package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<EjercicioDTO>> listar() {
        List<EjercicioDTO> lista = eS.list()
                .stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<EjercicioDTOInsert> registrar(@Valid @RequestBody EjercicioDTOInsert dto) {
        Users creador = null;
        if (dto.getIdCreadoPor() != null) {
            creador = uS.listId(dto.getIdCreadoPor())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el usuario creador con el id: " + dto.getIdCreadoPor()
                            )
                    );
        }
        Ejercicio ejercicio = modelMapper.map(dto, Ejercicio.class);
        ejercicio.setId(null);
        ejercicio.setCreadoPor(creador);
        ejercicio.setCreatedAt(LocalDateTime.now());
        ejercicio.setUpdatedAt(null);
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

    @PutMapping
    public ResponseEntity<EjercicioDTOInsert> actualizar(@Valid @RequestBody EjercicioDTOInsert dto) {
        Ejercicio existente = eS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un ejercicio con el id: " + dto.getId()
                        )
                );
        Users creador = null;
        if (dto.getIdCreadoPor() != null) {
            creador = uS.listId(dto.getIdCreadoPor())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "No existe el usuario creador con el id: " + dto.getIdCreadoPor()
                            )
                    );
        }
        Ejercicio ejercicio = modelMapper.map(dto, Ejercicio.class);
        ejercicio.setId(existente.getId());
        ejercicio.setCreadoPor(creador);
        ejercicio.setCreatedAt(existente.getCreatedAt());
        ejercicio.setUpdatedAt(LocalDateTime.now());
        eS.update(ejercicio);

        EjercicioDTOInsert responseDTO = modelMapper.map(ejercicio, EjercicioDTOInsert.class);
        return ResponseEntity.ok(responseDTO);
    }

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

    @GetMapping("/materias")
    public ResponseEntity<List<EjercicioDTO>> buscarPorMateria(@RequestParam String materia) {
        List<EjercicioDTO> lista = eS.obtenerPorMateria(materia)
                .stream()
                .map(ejercicio -> modelMapper.map(ejercicio, EjercicioDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }
}
