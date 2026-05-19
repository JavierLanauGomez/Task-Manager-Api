# Task Manager API

API REST para gestión de tareas, construida con Spring Boot y MySQL.

---

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.5 |
| Base de datos | MySQL |
| ORM | Spring Data JPA (Hibernate) |
| Validaciones | Jakarta Validation (Bean Validation) |
| Build | Maven |

---

## Estructura del proyecto

```
src/main/java/com/webtareas/tareas/
├── TareasApplication.java          # Punto de entrada de la aplicación
├── controller/
│   └── TareaController.java        # Recibe las peticiones HTTP y delega al service
├── service/
│   └── TareaService.java           # Lógica de negocio
├── repository/
│   └── TareaRepository.java        # Acceso a la base de datos (Spring Data JPA)
├── model/
│   └── Tarea.java                  # Entidad JPA (representa la tabla "tareas")
└── exception/
    ├── TareaNotFoundException.java  # Excepción personalizada para tarea no encontrada
    └── GlobalExceptionHandler.java  # Manejador global de errores (respuestas JSON)
```

---

## Cómo funciona (flujo de una petición)

```
Cliente (Postman / frontend)
        │
        ▼
TareaController       ← recibe la petición HTTP, valida el body con @Valid
        │
        ▼
TareaService          ← contiene la lógica (buscar, guardar, actualizar, eliminar)
        │
        ▼
TareaRepository       ← habla con MySQL a través de JPA
        │
        ▼
Base de datos (MySQL)
```

Si algo falla (tarea no encontrada, datos inválidos), el error pasa por `GlobalExceptionHandler` que devuelve un JSON limpio en lugar de una página de error HTML.

---

## Modelo de datos

**Tabla: `tareas`**

| Campo | Tipo | Restricciones |
|---|---|---|
| id | BIGINT | PK, auto-increment |
| titulo | VARCHAR | Obligatorio, entre 3 y 100 caracteres |
| descripcion | VARCHAR | Opcional, máximo 255 caracteres |
| completada | BOOLEAN | true / false |

---

## Endpoints

Base URL: `http://localhost:8080/api/tareas`

| Método | URL | Descripción |
|---|---|---|
| GET | `/api/tareas` | Obtener todas las tareas |
| GET | `/api/tareas/{id}` | Obtener una tarea por ID |
| POST | `/api/tareas` | Crear una nueva tarea |
| PUT | `/api/tareas/{id}` | Actualizar una tarea existente |
| DELETE | `/api/tareas/{id}` | Eliminar una tarea |

### Ejemplo de body (POST / PUT)

```json
{
  "titulo": "Aprender Spring Boot",
  "descripcion": "Estudiar la documentación oficial",
  "completada": false
}
```

### Respuesta de error — validación fallida (400)

```json
{
  "titulo": "El título no puede estar vacío"
}
```

### Respuesta de error — tarea no encontrada (404)

```json
{
  "error": "Tarea con id 99 no encontrada"
}
```

---

## Configuración local

**`src/main/resources/application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tareas_db
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update   # Crea/actualiza la tabla automáticamente
spring.jpa.show-sql=true               # Muestra las queries SQL en consola
```

> La base de datos `tareas_db` debe existir en MySQL antes de arrancar.  
> La tabla `tareas` la crea Hibernate automáticamente gracias a `ddl-auto=update`.

---

## Cómo arrancar

```bash
./mvnw spring-boot:run
```

---

## Lo que se ha implementado

- [x] CRUD completo de tareas
- [x] Validaciones en el modelo (`@NotBlank`, `@Size`)
- [x] Activación de validaciones en el controller (`@Valid`)
- [x] Excepción personalizada `TareaNotFoundException` (devuelve 404)
- [x] Manejador global de errores `GlobalExceptionHandler` (devuelve JSON en todos los errores)

## Próximos pasos

- [ ] DTOs (separar la entidad de lo que se expone en la API)
- [ ] Paginación en el listado de tareas
- [ ] Tests de integración
- [ ] Seguridad con Spring Security + JWT
