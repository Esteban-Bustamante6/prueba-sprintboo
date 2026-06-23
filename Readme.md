TalentBoard - Sistema de Gestión de Talento

TalentBoard es una aplicación backend desarrollada en Java con Spring Boot diseñada para optimizar el proceso de reclutamiento. Permite gestionar vacantes, usuarios (candidatos y reclutadores), postulaciones y la programación de entrevistas.

Tecnologías Utilizadas

Lenguaje: Java 17+

Framework: Spring Boot 3.x

Gestión de Dependencias: Maven

Base de Datos: H2 Database (En memoria, ideal para desarrollo)

Persistencia: Spring Data JPA / Hibernate

Seguridad: Spring Security (JWT)

Documentación de Entidades: Lombok

Validaciones: Bean Validation

Instrucciones de Instalación

Clonar el repositorio:

git clone <url-de-tu-repositorio>
cd TalentBoard


Requisitos previos:

Tener instalado JDK 17 o superior.

Tener instalado Maven.

Compilar el proyecto:
En la raíz del proyecto, ejecuta:

mvn clean install


Instrucciones de Ejecución

Una vez compilado, puedes ejecutar la aplicación directamente con Maven:

mvn spring-boot:run


El servidor iniciará por defecto en el puerto 8080. Puedes acceder a la consola de H2 para visualizar la base de datos en http://localhost:8080/h2-console.

Variables de Entorno Requeridas

Para el funcionamiento completo, asegúrate de tener configurado en tu application.properties:

DB_URL: jdbc:h2:mem:talentdb

DB_USERNAME: sa

DB_PASSWORD: (vacío por defecto)

JWT_SECRET: (debe ser una cadena de texto larga para la firma de tokens)

Credenciales de Prueba

Al ser una base de datos en memoria (H2), los datos se reinician al detener la aplicación. Se recomienda realizar las pruebas creando primero:

Un Usuario (Reclutador): ID 1.

Una Vacante: Asignada al responsable con ID 1.

Un Candidato: Usuario con rol CANDIDATE.

Una Postulación: Relacionando el Candidato y la Vacante.

Evidencias de Funcionamiento

Gestión de Entrevistas: El sistema valida automáticamente que la fecha de la entrevista no sea anterior al momento actual, evitando errores de lógica de negocio.

Seguridad: Integración de Spring Security para proteger los endpoints según el rol del usuario (CANDIDATE vs RECRUITER).

Control de Excepciones: Se ha implementado un GlobalExceptionHandler que captura errores de negocio (BusinessException) y devuelve respuestas claras en formato JSON, evitando que el cliente vea errores crípticos del servidor.