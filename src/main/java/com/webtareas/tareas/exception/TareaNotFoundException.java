package com.webtareas.tareas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TareaNotFoundException extends RuntimeException {

    public TareaNotFoundException(Long id) {
        super("Tarea con id " + id + " no encontrada");
    }
}