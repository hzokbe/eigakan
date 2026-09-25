# Eigakan — Modelos (Models)

Cada modelo Eloquent corresponde a uma tabela do banco. Os relacionamentos N:N que possuem atributos próprios (Adicionar
à lista, Avaliar) viram tabelas pivot dedicadas, com model próprio; os que não possuem atributos (Favoritar, Possuir,
Produzir, Escrever) usam pivot simples via `belongsToMany`.

### User

**Tabela:** `users`

| Coluna                      | Tipo           |
|-----------------------------|----------------|
| `id`                        | uuid, PK       |
| `username`                  | string         |
| `email`                     | string, unique |
| `password`                  | string (hash)  |
| `role`                      | string         |
| `created_at` / `updated_at` | timestamp      |

**Relacionamentos:**

- `animeList()` —
  `belongsToMany(Anime::class, 'anime_list')->withPivot('status', 'progress', 'started_at', 'finished_at')`
- `mangaList()` —
  `belongsToMany(Manga::class, 'manga_list')->withPivot('status', 'progress', 'started_at', 'finished_at')`
- `animeRatings()` — `belongsToMany(Anime::class, 'anime_ratings')->withPivot('score')`
- `mangaRatings()` — `belongsToMany(Manga::class, 'manga_ratings')->withPivot('score')`
- `favoriteAnimes()` — `belongsToMany(Anime::class, 'anime_favorites')`
- `favoriteMangas()` — `belongsToMany(Manga::class, 'manga_favorites')`

### Anime

**Tabela:** `animes`

| Coluna                      | Tipo             |
|-----------------------------|------------------|
| `id`                        | uuid, PK         |
| `title`                     | string           |
| `japanese_title`            | string, nullable |
| `synopsis`                  | text, nullable   |
| `type`                      | string           |
| `episodes`                  | int, nullable    |
| `status`                    | string           |
| `aired_from`                | date, nullable   |
| `aired_to`                  | date, nullable   |
| `score`                     | float, nullable  |
| `image_source`              | string, nullable |
| `created_at` / `updated_at` | timestamp        |

**Relacionamentos:**

- `genres()` — `belongsToMany(Genre::class, 'anime_genre')`
- `characters()` — `belongsToMany(Character::class, 'anime_character')`
- `studios()` — `belongsToMany(Studio::class, 'anime_studio')`
- `usersWithInList()` —
  `belongsToMany(User::class, 'anime_list')->withPivot('status', 'progress', 'started_at', 'finished_at')`
- `ratings()` — `belongsToMany(User::class, 'anime_ratings')->withPivot('score')`
- `favoritedBy()` — `belongsToMany(User::class, 'anime_favorites')`

### Manga

**Tabela:** `mangas`

| Coluna                      | Tipo             |
|-----------------------------|------------------|
| `id`                        | uuid, PK         |
| `title`                     | string           |
| `japanese_title`            | string, nullable |
| `synopsis`                  | text, nullable   |
| `chapters`                  | int, nullable    |
| `volumes`                   | int, nullable    |
| `status`                    | string           |
| `published_from`            | date, nullable   |
| `published_to`              | date, nullable   |
| `score`                     | float, nullable  |
| `image_source`              | string, nullable |
| `created_at` / `updated_at` | timestamp        |

**Relacionamentos:**

- `genres()` — `belongsToMany(Genre::class, 'manga_genre')`
- `characters()` — `belongsToMany(Character::class, 'manga_character')`
- `authors()` — `belongsToMany(Author::class, 'author_manga')`
- `usersWithInList()` —
  `belongsToMany(User::class, 'manga_list')->withPivot('status', 'progress', 'started_at', 'finished_at')`
- `ratings()` — `belongsToMany(User::class, 'manga_ratings')->withPivot('score')`
- `favoritedBy()` — `belongsToMany(User::class, 'manga_favorites')`

### Genre

**Tabela:** `genres`

| Coluna                      | Tipo           |
|-----------------------------|----------------|
| `id`                        | uuid, PK       |
| `name`                      | string, unique |
| `description`               | text, nullable |
| `created_at` / `updated_at` | timestamp      |

**Relacionamentos:**

- `animes()` — `belongsToMany(Anime::class, 'anime_genre')`
- `mangas()` — `belongsToMany(Manga::class, 'manga_genre')`

### Studio

**Tabela:** `studios`

| Coluna                      | Tipo             |
|-----------------------------|------------------|
| `id`                        | uuid, PK         |
| `name`                      | string           |
| `japanese_name`             | string, nullable |
| `description`               | text, nullable   |
| `established`               | date, nullable   |
| `image_source`              | string, nullable |
| `created_at` / `updated_at` | timestamp        |

**Relacionamentos:**

- `animes()` — `belongsToMany(Anime::class, 'anime_studio')`

### Author

**Tabela:** `authors`

| Coluna                      | Tipo             |
|-----------------------------|------------------|
| `id`                        | uuid, PK         |
| `name`                      | string           |
| `last_name`                 | string, nullable |
| `japanese_name`             | string, nullable |
| `japanese_last_name`        | string, nullable |
| `birthday`                  | date, nullable   |
| `image_source`              | string, nullable |
| `created_at` / `updated_at` | timestamp        |

**Relacionamentos:**

- `mangas()` — `belongsToMany(Manga::class, 'author_manga')`

### Character

**Tabela:** `characters`

| Coluna                      | Tipo             |
|-----------------------------|------------------|
| `id`                        | uuid, PK         |
| `name`                      | string           |
| `last_name`                 | string, nullable |
| `japanese_name`             | string, nullable |
| `japanese_last_name`        | string, nullable |
| `age`                       | string, nullable |
| `gender`                    | string, nullable |
| `role`                      | string, nullable |
| `description`               | text, nullable   |
| `image_source`              | string, nullable |
| `created_at` / `updated_at` | timestamp        |

**Relacionamentos:**

- `animes()` — `belongsToMany(Anime::class, 'anime_character')`
- `mangas()` — `belongsToMany(Manga::class, 'manga_character')`
