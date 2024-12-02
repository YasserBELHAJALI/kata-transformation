# Kata de Transformation de Nombres

## Description

Cette application transforme un nombre entre 0 et 100 en une chaîne de caractères en suivant des règles spécifiques.

## Technologies Utilisées

- Java 17
- Maven
- Spring Boot
- Spring Batch
- JUnit 5
- Mockito
- Swagger (OpenAPI)
- Docker

## Fonctionnalités

- **API REST** : Un endpoint qui prend un nombre en entrée et retourne sa transformation.
- **Traitement Batch** : Lecture d'un fichier `.txt` en entrée et écriture des résultats dans un fichier de sortie.
- **Tests Unitaires** : Couverture des tests avec JUnit et Mockito.
- **Documentation API** : Accessible via Swagger UI.(par defaut local : http://localhost:8080/swagger-ui/index.html )
- **Containerisation** : Dockerfile pour déployer l'application dans un conteneur Docker.

## Installation et Exécution

### Prérequis

- Java 17
- Maven
- Docker (optionnel)

### Construction du Projet

```bash
mvn clean install
