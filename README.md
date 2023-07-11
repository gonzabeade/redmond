# Redmond

El siguiente repositorio es el trabajo final de la materia 72.41 Bases de Datos II @ ITBA. 
Hecho por:
* [Beade, Gonzalo Manuel](https://github.com/gbeade) - 61223
* [Cornidez, Milagros](https://github.com/mcornidez) - 61432
* [Morantes, Agustín](https://github.com/agustinmorantes) - 61306

La estuctura del repositorio es la siguiente: 

- El directorio `redmond` es un proyecto de Spring Boot que contiene la API del sistema de conexión interbancaria.
- El directorio `bank` es un proyecto de Spring Boot que contiene una API genérica implementada para un banco puntual.
- El directorio `web` contiene el frontend de la aplicación Redmond.

## Correr el proyecto
El proyecto es autocontenido y desplegable desde 0. Basta con correr `docker-compose up -d` para inicializar las bases de datos y habilitar los puertos. 

En codespaces se deben poner públicos los puertos:
- 80
- 8080
- 8081
- 8082
  (estos dos últimos son para ver las APIs de los bancos)
