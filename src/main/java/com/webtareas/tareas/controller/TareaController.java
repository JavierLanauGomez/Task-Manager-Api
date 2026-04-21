package com.webtareas.tareas.controller;


import com.webtareas.tareas.model.Tarea;
import com.webtareas.tareas.service.TareaService;
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
    public Tarea obtenerPorId(@PathVariable Long id) {
    return tareaService.obtenerPorId(id);
}

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        return tareaService.guardar(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        return tareaService.actualizar(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}