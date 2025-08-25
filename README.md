# eigakan

This project is a REST API that allows you to add, retrieve, update, and delete movies.

## Prerequisites

- [Docker](https://www.docker.com/)

## Technologies

- Java
- Spring Boot
- MongoDB
- Redis
- Docker
- Docker Compose

## How to Run

Create a file named `.env` in the root directory of the project and define the following environment variables to configure `MongoDB` access:

```
MONGO_DB=eigakan
MONGO_USER=hzokbe
MONGO_PASSWORD=hzokbe
```

These variables will be used by `Docker Compose` to initialize the services with the correct credentials.

To configure and start the application, run:

```bash
openssl genrsa > src/main/resources/private.key

openssl rsa -in src/main/resources/private.key -pubout -out src/main/resources/public.key

docker compose up -d
```

## Endpoints

### `POST /movies`

Request example:

```json
{
    "id": "5a726dfb-4e29-41fe-8c32-04c1b13718bb",
    "title": "Interestelar",
    "genre": "SCIENCE_FICTION"
}
```

Response example:

`201 CREATED`

### `GET /movies/all`

Response example:

`200 OK`

```json
[
    {
        "id": "5a726dfb-4e29-41fe-8c32-04c1b13718bb",
        "title": "Interestelar", 
        "genre": "SCIENCE_FICTION"
    },
    {
        "id": "03e9eae9-d0b1-44c2-a9ff-70419082ecfc",
        "title": "Pulp Fiction",
        "genre": "CRIME"
    }
]
```

### `GET /movies`

Response example:

`200 OK`

```json
{
    "content": [
        {
            "id": "5a726dfb-4e29-41fe-8c32-04c1b13718bb",
            "title": "Interestelar",
            "genre": "SCIENCE_FICTION"
        },
        {
            "id": "03e9eae9-d0b1-44c2-a9ff-70419082ecfc",
            "title": "Pulp Fiction",
            "genre": "CRIME"
        }
    ],
    "number": 0,
    "totalPages": 1,
    "size": 20,
    "totalElements": 2,
    "first": true,
    "last": true
}
```

### `GET /movies/{id}`

Response example:

`200 OK`

```json
{
    "id": "5a726dfb-4e29-41fe-8c32-04c1b13718bb",
    "title": "Interestelar",
    "genre": "SCIENCE_FICTION"
}
```

### `PUT /movies/{id}`

Request example:

```json
{
    "title": "John Wick",
    "genre": "ACTION"
}
```

Response example:

`200 OK`

```json
{
    "id": "5a726dfb-4e29-41fe-8c32-04c1b13718bb",
    "title": "John Wick",
    "genre": "ACTION"
}
```

### `DELETE /movies/{id}`

Response example:

`201 NO CONTENT`
