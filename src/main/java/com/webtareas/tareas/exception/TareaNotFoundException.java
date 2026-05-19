package com.webtareas.tareas.exception;

public class TareaNotFoundException extends RuntimeException {

    public TareaNotFoundException(Long id) {
        super("Tarea con id " + id + " no encontrada");
    }
}