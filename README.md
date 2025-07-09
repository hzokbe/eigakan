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
    "title": "Interestelar",
    "genre": "SCIENCE_FICTION"
}
```

Response example:

`201 CREATED`
