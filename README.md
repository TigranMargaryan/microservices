# Microservices 

### User notes

In this application I used the following technologies:

* Java
* Maven
* MySQL
* Docker
* Liquibase
* Spring Security oauth2

By default, in application.properties MySQL connection parametors are username=root password='root'

#### Structure

```
├── microservices
|   ├── note-service
|   |   ├────────── config  -----# 
|   |   ├────────── context -----#
|   |   ├────────── controller---#
|   |   ├────────── domain  -----#
|   |   ├────────── repository---#
|   |   └────────── service -----# 
|   |
|   ├── user-service
|   |   ├────────── config  -----# 
|   |   ├────────── domain  -----#
|   |   ├────────── service -----#
└───────└────────── repostory ---#
```
-------

#### Run application

To run the application, go to the main folder and run `docker-compose up`

Due to some oath2 security problems, the note-service doesn't work correctly with the docker, so it needs to run manually. Thank you :)