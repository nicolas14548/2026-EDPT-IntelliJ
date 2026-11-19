package pe.edu.upc.demovi.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.demovi.dtos.CantidadEvaluacionesDTO;
import pe.edu.upc.demovi.dtos.EvaluacionDTO;
import pe.edu.upc.demovi.entities.Evaluacion;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demovi.servicesinterfaces.IEvaluacionService;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {
    private final IEvaluacionService eS;
    private final IUsersService uS;
    private final ModelMapper modelMapper;

    public EvaluacionController(IEvaluacionService eS, IUsersService uS, ModelMapper modelMapper) {
        this.eS = eS;
        this.uS = uS;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> listar() {
        List<EvaluacionDTO> lista = eS.list()
                .stream()
                .map(evaluacion -> modelMapper.map(evaluacion, EvaluacionDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<EvaluacionDTO> registrar(@Valid @RequestBody EvaluacionDTO dto) {
        Users estudiante = uS.listId(dto.getIdEstudiante())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el estudiante con el id: " + dto.getIdEstudiante()
                        )
                );
        Evaluacion evaluacion = modelMapper.map(dto, Evaluacion.class);
        evaluacion.setId(null);
        evaluacion.setFechaFin("FINALIZADA".equals(evaluacion.getEstado()) ? LocalDateTime.now() : null);
        evaluacion.setEstudiante(estudiante);
        eS.insert(evaluacion);

        EvaluacionDTO responseDTO = modelMapper.map(evaluacion, EvaluacionDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(evaluacion.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> buscarPorId(@PathVariable Long id) {
        Evaluacion evaluacion = eS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una evaluación con el id: " + id
                        )
                );
        EvaluacionDTO dto = modelMapper.map(evaluacion, EvaluacionDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<EvaluacionDTO> actualizar(@Valid @RequestBody EvaluacionDTO dto) {
        Evaluacion existente = eS.listId(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una evaluación con el id: " + dto.getId()
                        )
                );
        Users estudiante = uS.listId(dto.getIdEstudiante())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe el estudiante con el id: " + dto.getIdEstudiante()
                        )
                );
        Evaluacion evaluacion = modelMapper.map(dto, Evaluacion.class);
        evaluacion.setId(existente.getId());
        evaluacion.setFechaFin("FINALIZADA".equals(evaluacion.getEstado()) ? LocalDateTime.now() : null);
        evaluacion.setEstudiante(estudiante);
        eS.update(evaluacion);

        EvaluacionDTO responseDTO = modelMapper.map(evaluacion, EvaluacionDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Evaluacion evaluacion = eS.listId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una evaluación con el id: " + id
                        )
                );
        eS.delete(evaluacion.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<EvaluacionDTO>> historialPorEstudiante(@PathVariable Long idEstudiante) {
        List<EvaluacionDTO> lista = eS.obtenerPorEstudiante(idEstudiante)
                .stream()
                .map(evaluacion -> modelMapper.map(evaluacion, EvaluacionDTO.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/cantidades")
    public ResponseEntity<List<CantidadEvaluacionesDTO>> contarPorEstudiante() {
        List<CantidadEvaluacionesDTO> lista = eS.contarPorEstudiante()
                .stream()
                .map(item -> {
                    CantidadEvaluacionesDTO dto = new CantidadEvaluacionesDTO();
                    dto.setNombre((String) item[0]);
                    dto.setCantidad(((Number) item[1]).longValue());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(lista);
    }
}
