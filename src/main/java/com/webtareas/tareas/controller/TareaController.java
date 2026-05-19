package com.webtareas.tareas.controller;

import com.webtareas.tareas.model.Tarea;
import com.webtareas.tareas.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> obtenerTodas() {
        return tareaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Tarea obtenerPorId(@PathVariable long id) {
        return tareaService.obtenerPorId(id);
    }

    // Fallo 1: faltaba @Valid → la validación de Tarea se ignoraba silenciosamente
    // Fallo 2: devolvía 200 OK; un recurso creado debe devolver 201 Created
    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody Tarea tarea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.guardar(tarea));
    }

    // Fallo 1: faltaba @Valid en el PUT también
    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable long id, @Valid @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    // Fallo 2: devolvía 200 OK; una eliminación exitosa debe devolver 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}