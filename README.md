# eigakan

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

To start the application, run:

```bash
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
