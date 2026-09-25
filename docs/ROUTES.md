# Eigakan — Rotas

As rotas seguem a convenção de recursos do Laravel (`Route::resource`) onde possível, agrupadas por contexto e mapeadas
aos requisitos funcionais.

### Autenticação (RF01)

| Método | URI         | Middleware |
|--------|-------------|------------|
| GET    | `/register` | `guest`    |
| POST   | `/register` | `guest`    |
| GET    | `/login`    | `guest`    |
| POST   | `/login`    | `guest`    |
| POST   | `/logout`   | `auth`     |

### Catálogo público (RF02, RF03, RF04, RF08)

| Método | URI                       | Controller@Action          | Middleware |
|--------|---------------------------|----------------------------|------------|
| GET    | `/`                       | `HomeController@index`     | —          |
| GET    | `/animes`                 | `AnimeController@index`    | —          |
| GET    | `/animes/{anime}`         | `AnimeController@show`     | —          |
| GET    | `/mangas`                 | `MangaController@index`    | —          |
| GET    | `/mangas/{manga}`         | `MangaController@show`     | —          |
| GET    | `/studios/{studio}`       | `StudioController@show`    | —          |
| GET    | `/authors/{author}`       | `AuthorController@show`    | —          |
| GET    | `/characters/{character}` | `CharacterController@show` | —          |
| GET    | `/search`                 | `SearchController@index`   | —          |

### Lista pessoal (RF05)

| Método | URI                    | Controller@Action             | Middleware |
|--------|------------------------|-------------------------------|------------|
| GET    | `/my/list`             | `UserListController@index`    | `auth`     |
| POST   | `/animes/{anime}/list` | `AnimeListController@store`   | `auth`     |
| PUT    | `/animes/{anime}/list` | `AnimeListController@update`  | `auth`     |
| DELETE | `/animes/{anime}/list` | `AnimeListController@destroy` | `auth`     |
| POST   | `/mangas/{manga}/list` | `MangaListController@store`   | `auth`     |
| PUT    | `/mangas/{manga}/list` | `MangaListController@update`  | `auth`     |
| DELETE | `/mangas/{manga}/list` | `MangaListController@destroy` | `auth`     |

### Avaliação (RF06)

| Método | URI                      | Controller@Action               | Middleware |
|--------|--------------------------|---------------------------------|------------|
| POST   | `/animes/{anime}/rating` | `AnimeRatingController@store`   | `auth`     |
| PUT    | `/animes/{anime}/rating` | `AnimeRatingController@update`  | `auth`     |
| DELETE | `/animes/{anime}/rating` | `AnimeRatingController@destroy` | `auth`     |
| POST   | `/mangas/{manga}/rating` | `MangaRatingController@store`   | `auth`     |
| PUT    | `/mangas/{manga}/rating` | `MangaRatingController@update`  | `auth`     |
| DELETE | `/mangas/{manga}/rating` | `MangaRatingController@destroy` | `auth`     |

### Favoritos (RF07)

| Método | URI                        | Controller@Action                 | Middleware |
|--------|----------------------------|-----------------------------------|------------|
| GET    | `/my/favorites`            | `UserFavoriteController@index`    | `auth`     |
| POST   | `/animes/{anime}/favorite` | `AnimeFavoriteController@store`   | `auth`     |
| DELETE | `/animes/{anime}/favorite` | `AnimeFavoriteController@destroy` | `auth`     |
| POST   | `/mangas/{manga}/favorite` | `MangaFavoriteController@store`   | `auth`     |
| DELETE | `/mangas/{manga}/favorite` | `MangaFavoriteController@destroy` | `auth`     |

### Administração (RF09, RF10, RF11, RF12)

Todas as rotas abaixo têm prefixo `/admin` e middleware `auth`, `role:admin`.

| Método        | URI                                            | Controller@Action                                |
|---------------|------------------------------------------------|--------------------------------------------------|
| GET           | `/admin/users`                                 | `Admin\UserController@index`                     |
| PUT           | `/admin/users/{user}/role`                     | `Admin\UserController@updateRole`                |
| DELETE        | `/admin/users/{user}`                          | `Admin\UserController@destroy`                   |
| resource      | `/admin/animes`                                | `Admin\AnimeController`                          |
| resource      | `/admin/mangas`                                | `Admin\MangaController`                          |
| resource      | `/admin/genres`                                | `Admin\GenreController`                          |
| resource      | `/admin/studios`                               | `Admin\StudioController`                         |
| resource      | `/admin/authors`                               | `Admin\AuthorController`                         |
| resource      | `/admin/characters`                            | `Admin\CharacterController`                      |
| POST / DELETE | `/admin/animes/{anime}/genres/{genre}`         | `Admin\AnimeGenreController@store / destroy`     |
| POST / DELETE | `/admin/animes/{anime}/studios/{studio}`       | `Admin\AnimeStudioController@store / destroy`    |
| POST / DELETE | `/admin/animes/{anime}/characters/{character}` | `Admin\AnimeCharacterController@store / destroy` |
| POST / DELETE | `/admin/mangas/{manga}/genres/{genre}`         | `Admin\MangaGenreController@store / destroy`     |
| POST / DELETE | `/admin/mangas/{manga}/authors/{author}`       | `Admin\MangaAuthorController@store / destroy`    |
| POST / DELETE | `/admin/mangas/{manga}/characters/{character}` | `Admin\MangaCharacterController@store / destroy` |

> `resource` indica as sete rotas padrão do Laravel (`index`, `create`, `store`, `show`, `edit`, `update`, `destroy`).
