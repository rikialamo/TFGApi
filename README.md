# Gestión Horario

## Desarrollado para el Ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM)

### Alumno: Ricardo Álamo Fernández

---

## Índice

1. [Introducción](#introducción)
2. [Funcionalidades y Tecnologías](#funcionalidades-y-tecnologías)
3. [Guía de Instalación](#guía-de-instalación)
4. [Guía de Uso](#guía-de-uso)
5. [Documentación](#documentación)
6. [Interfaz de Usuario en Figma](#interfaz-de-usuario-en-figma)
7. [Conclusión](#conclusión)
8. [Contribuciones, Agradecimientos y Referencias](#contribuciones-agradecimientos-y-referencias)
9. [Licencias](#licencias)
10. [Contacto](#contacto)

---

## Introducción

### Descripción del Proyecto
El proyecto "Gestión Horari" es una aplicación diseñada para gestionar y registrar los horarios de entrada y salida de los empleados en una organización. Este sistema permite a los administradores supervisar la asistencia y tener un mejor control de los empleados.

### Justificación
La gestión manual de los horarios es propensa a errores y consume mucho tiempo. Este proyecto automatiza el proceso, asegurando precisión y eficiencia.

### Objetivos
- Automatizar el registro de entradas y salidas de los empleados.
- Facilitar la supervisión de la asistencia.
- Proporcionar una interfaz amigable y accesible para todos los usuarios.

### Motivación
La motivación detrás de este proyecto es mejorar la productividad y eficiencia en la gestión de horarios en las empresas, aprovechando la tecnología para reducir errores y optimizar procesos.

---

## Funcionalidades y Tecnologías

### Funcionalidades
- Registro de entradas y salidas.
- Gestión de usuarios (creación, actualización y eliminación).
- Obtención de los empleados con faltas

### Tecnologías Utilizadas
- **Backend**: Spring Boot, JPA, Hibernate
- **Base de Datos**: MySQL
- **Seguridad**: Spring Security
- **Testing**: JUnit, Mockito
- **Frontend**: Figma (prototipo de interfaz)
- **Herramientas**: Maven, Git

---

## Guía de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu_usuario/gestion-horaria-inteligente.git
   cd gestionHorarioTFG
   ```

2. **Configurar la base de datos**
   - Crear una base de datos MySQL.
   - Actualizar las credenciales en `src/main/resources/application.properties`.

3. **Compilar y ejecutar la aplicación**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## Guía de Uso para usuarios

1. **Registro de Entrada**
   - Endpoint: `POST /api/usuario/entrada`
   - Cuerpo de la solicitud:
     ```json
     {
       "correo": "usuario@ejemplo.com",
       "contrasena": "tu_contrasena"
     }
     ```

2. **Registro de Salida**
   - Endpoint: `POST /api/usuario/salida`
   - Cuerpo de la solicitud:
     ```json
     {
       "correo": "usuario@ejemplo.com",
       "contrasena": "tu_contrasena"
     }
     ```

3. **Actualizar Contraseña**
   - Endpoint: `PUT /api/usuario/actualizarContrasena`
   - Cuerpo de la solicitud:
     ```json
     {
       "correo": "usuario@ejemplo.com",
       "contrasena": "tu_contrasena_actual",
       "nuevacontrasena": "tu_nueva_contrasena"
     }
     ```

---

# Guía de Uso para Administradores

## Endpoints del `AdminController`

---

### 1. Crear Usuario

**Descripción**: Este endpoint permite a los administradores crear un nuevo usuario en el sistema.

**Endpoint**: `POST /api/admin/crearUsuario`

**Cuerpo de la solicitud**:
```json
{
  "nombre": "Nombre del Usuario",
  "correo": "correo@ejemplo.com",
  "contrasena": "contrasena123"
}
```

**Respuesta**:
```json
{
  "id": 1,
  "nombre": "Nombre del Usuario",
  "correo": "correo@ejemplo.com"
}
```

---

### 2. Crear Administrador

**Descripción**: Este endpoint permite a los administradores crear un nuevo administrador en el sistema.

**Endpoint**: `POST /api/admin/crearAdmin`

**Cuerpo de la solicitud**:
```json
{
  "nombre": "Nombre del Administrador",
  "correo": "admin@ejemplo.com",
  "contrasena": "admin123"
}
```

**Respuesta**:
```json
{
  "id": 1,
  "nombre": "Nombre del Administrador",
  "correo": "admin@ejemplo.com"
}
```

---

### 3. Actualizar Usuario

**Descripción**: Este endpoint permite a los administradores actualizar la información de un usuario existente.

**Endpoint**: `PUT /api/admin/actualizarUsuario`

**Cuerpo de la solicitud**:
```json
{
  "nombre": "Nuevo Nombre",
  "correo": "nuevoCorreo@ejemplo.com",
  "contrasena": "nuevaContrasena123"
}
```

**Respuesta**:
```json
{
  "id": 1,
  "nombre": "Nuevo Nombre",
  "correo": "nuevoCorreo@ejemplo.com"
}
```

---

### 4. Eliminar Usuario

**Descripción**: Este endpoint permite a los administradores eliminar un usuario del sistema.

**Endpoint**: `DELETE /api/admin/eliminarUsuario`

**Cuerpo de la solicitud**:
```json
{
  "correo": "correo@ejemplo.com",
  "contrasena": "contrasena123"
}
```

**Respuesta**:
```json
{
  "mensaje": "Usuario eliminado exitosamente"
}
```

---

### 5. Listar Usuarios

**Descripción**: Este endpoint permite a los administradores listar todos los usuarios del sistema.

**Endpoint**: `GET /api/admin/listarUsuarios`

**Respuesta**:
```json
[
  {
    "id": 1,
    "nombre": "Usuario 1",
    "correo": "usuario1@ejemplo.com"
  },
  {
    "id": 2,
    "nombre": "Usuario 2",
    "correo": "usuario2@ejemplo.com"
  }
]
```

---

### 6. Listar Usuarios con Rol "USER"

**Descripción**: Este endpoint permite a los administradores listar todos los usuarios con el rol "USER".

**Endpoint**: `GET /api/admin/listarUsuariosUser`

**Respuesta**:
```json
[
  {
    "id": 1,
    "nombre": "Usuario 1",
    "correo": "usuario1@ejemplo.com"
  },
  {
    "id": 2,
    "nombre": "Usuario 2",
    "correo": "usuario2@ejemplo.com"
  }
]
```

---

### 7. Listar Usuarios con Rol "ADMIN"

**Descripción**: Este endpoint permite a los administradores listar todos los usuarios con el rol "ADMIN".

**Endpoint**: `GET /api/admin/listarUsuariosAdmin`

**Respuesta**:
```json
[
  {
    "id": 1,
    "nombre": "Administrador 1",
    "correo": "admin1@ejemplo.com"
  },
  {
    "id": 2,
    "nombre": "Administrador 2",
    "correo": "admin2@ejemplo.com"
  }
]
```

---

### 8. Obtener Usuarios sin Fichaje en la Semana Anterior

**Descripción**: Este endpoint permite a los administradores obtener una lista de usuarios que no han registrado su entrada/salida en una semana específica.

**Endpoint**: `POST /api/admin/usuariosSinFichajeSemanaAnterior`

**Cuerpo de la solicitud**:
```json
{
  "mes": 5,
  "díaInicio": 1,
  "diaFin": 7
}
```

**Respuesta**:
```json
[
  {
    "id": 1,
    "nombre": "Usuario 1",
    "correo": "usuario1@ejemplo.com"
  },
  {
    "id": 2,
    "nombre": "Usuario 2",
    "correo": "usuario2@ejemplo.com"
  }
]
```


---

## Documentación

Para una documentación más detallada de la API, visita [Documentación API](URL_DE_TU_DOCUMENTACION).

---

## Interfaz de Usuario en Figma

Puedes visualizar el prototipo de la interfaz de usuario en Figma [aquí](URL_DE_TU_FIGMA).

---

## Conclusión

Este proyecto ha permitido explorar y aplicar múltiples tecnologías para resolver un problema práctico en la gestión horaria. La automatización de este proceso no solo mejora la precisión y eficiencia, sino que también facilita la supervisión y administración de los empleados en cualquier organización.

---

## Contribuciones, Agradecimientos y Referencias

### Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o crea un pull request en el [repositorio del proyecto](URL_DEL_REPOSITORIO).

### Agradecimientos
- A mis profesores y compañeros del ciclo DAM por su apoyo y colaboración.

### Referencias
- Documentación de [Spring Boot](https://spring.io/projects/spring-boot)
- Documentación de [MySQL](https://dev.mysql.com/doc/)

---

## Licencias

Este proyecto está licenciado bajo la [MIT License](URL_DE_LA_LICENCIA).

---

## Contacto

Para cualquier consulta o sugerencia, puedes contactarme a través de:
- **Correo Electrónico**: rikialamo@gmail.com
- **GitHub**: [rikialamo](https://github.com/rikialamo)
- **LinkedIn**: [Ricardo Álamo Fernández](https://www.linkedin.com/in/ricardo-%C3%A1lamo-fern%C3%A1ndez-2a9782292/)

---

¡Gracias por tu interés en el proyecto "Gestión Horaria Inteligente"!