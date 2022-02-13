![example branch parameter](https://github.com/wmaliga/note-share/actions/workflows/maven.yml/badge.svg?branch=master)

# NoteShare Demo Application

## Development

### Features
* Sharing public and private notes
* Public notes can be listed by all users
* Every note may have an expiration time

### Prerequisites
* JDK 11 ``sudo apt install openjdk-11-jdk``
* Maven ``sudo apt install maven``
* Node ``nvm use v16.13.2``

### Running backend
```shell
cd %PRJ_DIR%/note-share-service
mvn clean install
mvn spring-boot:run
```

### Running frontend
```shell
cd %PRJ_DIR%/note-share-gui/src/main/angular
npm install
npm run start
```

Application will be available under:
```http://localhost:4200```

## Production
```shell
mvn clean install
docker-compose up
```

Application will be available under:
```http://localhost:80```

## What can be done better?

### Backend
* Missing JavaDoc documentation
* Secure incoming text data from the malicious scripts
* Dozer mapping does not work to well with immutable objects without default constructors

### Frontend
* Better error handling
* Notes list pagination
* Karma tests are missing

### TODO
* Add new line handling when note is shared