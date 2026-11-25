package pe.edu.upc.demovi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.demovi.dtos.EvaluacionResumenDTO;
import pe.edu.upc.demovi.dtos.ProgresoEstudianteDTO;
import pe.edu.upc.demovi.entities.Users;
import pe.edu.upc.demovi.exceptions.ResourceNotFoundException;
import pe.edu.upc.demovi.servicesinterfaces.IEvaluacionService;
import pe.edu.upc.demovi.servicesinterfaces.IUsersService;

import java.util.List;

@RestController
@RequestMapping("/api/apoderados")
public class ApoderadoController {
    private final IUsersService usersService;
    private final IEvaluacionService evaluacionService;

    public ApoderadoController(IUsersService usersService, IEvaluacionService evaluacionService) {
        this.usersService = usersService;
        this.evaluacionService = evaluacionService;
    }

    @PreAuthorize("hasRole('APODERADO')")
    @GetMapping("/progreso")
    public ResponseEntity<List<ProgresoEstudianteDTO>> progreso(Authentication authentication) {
        Users apoderado = usersService.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el usuario: " + authentication.getName()));

        List<ProgresoEstudianteDTO> progreso = usersService
                .obtenerEstudiantesPorApoderado(apoderado.getId())
                .stream()
                .map(estudiante -> {
                    ProgresoEstudianteDTO dto = new ProgresoEstudianteDTO();
                    dto.setId(estudiante.getId());
                    dto.setNombre(estudiante.getNombre());
                    dto.setApellido(estudiante.getApellido());
                    dto.setGrado(estudiante.getGrado());
                    dto.setSeccion(estudiante.getSeccion());
                    dto.setEvaluaciones(evaluacionService
                            .obtenerPorEstudiante(estudiante.getId())
                            .stream()
                            .map(evaluacion -> {
                                EvaluacionResumenDTO resumen = new EvaluacionResumenDTO();
                                resumen.setId(evaluacion.getId());
                                resumen.setTipo(evaluacion.getTipo());
                                resumen.setEstado(evaluacion.getEstado());
                                resumen.setTotalEjercicios(evaluacion.getTotalEjercicios());
                                resumen.setFechaInicio(evaluacion.getFechaInicio());
                                resumen.setFechaFin(evaluacion.getFechaFin());
                                resumen.setPuntaje(evaluacion.getPuntaje());
                                return resumen;
                            })
                            .toList());
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(progreso);
    }
}
