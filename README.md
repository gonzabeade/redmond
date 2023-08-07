# Redmond

This repository contains the final project for the course 72.41 Database Systems II @ ITBA. The aim of the project is to create a simple payments simple that integrates multiple types of database systems: Postgres, Redis, Neo4J, MongoDB, ElasticSearch and Kafka as a message queue. 

The structure of the repository is as follows:  

- The `redmond` directory is a Spring Boot project containing the API for the interbank connection system.
- The `bank` directory is a Spring Boot project containing a generic API implemented for a specific bank.
- The `web` directory contains the frontend for the Redmond application.

## Running the project
The project is self-contained and deployable from scratch - there is no need to manually set up the databases. It is sufficient to run `docker-compose up -d` to initialize the databases and enable the ports.

If using Codespaces, the following ports must be made public: 80, 8080, 8081, 8082. 

## Authors

* [Beade, Gonzalo Manuel](https://github.com/gbeade)
* [Cornidez, Milagros](https://github.com/mcornidez)
* [Morantes, Agustín](https://github.com/agustinmorantes)
