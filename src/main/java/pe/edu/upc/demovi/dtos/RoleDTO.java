package pe.edu.upc.demovi.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class RoleDTO {
    private Long id;
    @NotBlank(message = "El nombre del rol es obligatorio")
    private String nombreRol;
    private String descripcionRol;
    private LocalDateTime fechaCreacionRol;
    private LocalDateTime actualizadoEnRol;
    private String creadoPorNombre;
    private String actualizadoPorNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public LocalDateTime getFechaCreacionRol() {
        return fechaCreacionRol;
    }

    public void setFechaCreacionRol(LocalDateTime fechaCreacionRol) {
        this.fechaCreacionRol = fechaCreacionRol;
    }

    public LocalDateTime getActualizadoEnRol() {
        return actualizadoEnRol;
    }

    public void setActualizadoEnRol(LocalDateTime actualizadoEnRol) {
        this.actualizadoEnRol = actualizadoEnRol;
    }

    public String getCreadoPorNombre() {
        return creadoPorNombre;
    }

    public void setCreadoPorNombre(String creadoPorNombre) {
        this.creadoPorNombre = creadoPorNombre;
    }

    public String getActualizadoPorNombre() {
        return actualizadoPorNombre;
    }

    public void setActualizadoPorNombre(String actualizadoPorNombre) {
        this.actualizadoPorNombre = actualizadoPorNombre;
    }import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RolDTO } from '../models/RolDTO';
import { RolInsertDTO } from '../models/RolInsertDTO';

    @Injectable({
            providedIn: 'root',
})
export class Rolservice {
    private apiURL = `${environment.base}/api/roles`;

    constructor(private http: HttpClient) { }

    getRol(): Observable<RolDTO[]> {
        return this.http.get<RolDTO[]>(this.apiURL);
    }

    deleteRol(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiURL}/${id}`);
    }

    insertRol(dto: RolInsertDTO): Observable<RolDTO> {
        return this.http.post<RolDTO>(this.apiURL, dto);
    }

    listId(id: number): Observable<RolDTO> {
        return this.http.get<RolDTO>(`${this.apiURL}/${id}`);
    }

    update(dto: RolInsertDTO): Observable<RolDTO> {
        return this.http.put<RolDTO>(this.apiURL, dto);
    }
}

}
