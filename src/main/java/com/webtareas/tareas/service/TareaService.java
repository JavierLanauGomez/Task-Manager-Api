package com.webtareas.tareas.service;

import com.webtareas.tareas.exception.TareaNotFoundException;
import com.webtareas.tareas.model.Tarea;
import com.webtareas.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> obtenerTodas() {
        return tareaRepository.findAll();
    }

    public Tarea obtenerPorId(long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
    }

    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(long id, Tarea tareaActualizada) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
        tarea.setTitulo(tareaActualizada.getTitulo());
        tarea.setDescripcion(tareaActualizada.getDescripcion());
        tarea.setCompletada(tareaActualizada.isCompletada());
        return tareaRepository.save(tarea);
    }

    public void eliminar(long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException(id));
        tareaRepository.delete(tarea);
    }
}