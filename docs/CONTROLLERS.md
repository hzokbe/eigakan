# Eigakan — Controllers

Derivados diretamente das rotas. Cada controller é responsável por uma única entidade ou relação, mantendo-se enxuto.

### Catálogo público

| Controller            | Métodos         | Responsabilidade              |
|-----------------------|-----------------|-------------------------------|
| `HomeController`      | `index`         | Página inicial (home)         |
| `AnimeController`     | `index`, `show` | Listagem e detalhes de animes |
| `MangaController`     | `index`, `show` | Listagem e detalhes de mangás |
| `StudioController`    | `show`          | Detalhes de um estúdio        |
| `AuthorController`    | `show`          | Detalhes de um autor          |
| `CharacterController` | `show`          | Detalhes de um personagem     |
| `SearchController`    | `index`         | Busca e filtro por gênero     |

### Lista pessoal

| Controller            | Métodos                      | Responsabilidade                                |
|-----------------------|------------------------------|-------------------------------------------------|
| `UserListController`  | `index`                      | Exibe a lista pessoal do usuário logado         |
| `AnimeListController` | `store`, `update`, `destroy` | Adiciona/edita/remove um anime da lista pessoal |
| `MangaListController` | `store`, `update`, `destroy` | Adiciona/edita/remove um mangá da lista pessoal |

### Avaliação

| Controller              | Métodos                      | Responsabilidade                          |
|-------------------------|------------------------------|-------------------------------------------|
| `AnimeRatingController` | `store`, `update`, `destroy` | Cria/edita/remove a avaliação de um anime |
| `MangaRatingController` | `store`, `update`, `destroy` | Cria/edita/remove a avaliação de um mangá |

### Favoritos

| Controller                | Métodos            | Responsabilidade                      |
|---------------------------|--------------------|---------------------------------------|
| `UserFavoriteController`  | `index`            | Exibe os favoritos do usuário logado  |
| `AnimeFavoriteController` | `store`, `destroy` | Marca/desmarca um anime como favorito |
| `MangaFavoriteController` | `store`, `destroy` | Marca/desmarca um mangá como favorito |

### Administração

| Controller                       | Métodos                          | Responsabilidade                         |
|----------------------------------|----------------------------------|------------------------------------------|
| `Admin\UserController`           | `index`, `updateRole`, `destroy` | Gerencia usuários (papel, remoção)       |
| `Admin\AnimeController`          | `resource`                       | CRUD de animes                           |
| `Admin\MangaController`          | `resource`                       | CRUD de mangás                           |
| `Admin\GenreController`          | `resource`                       | CRUD de gêneros                          |
| `Admin\StudioController`         | `resource`                       | CRUD de estúdios                         |
| `Admin\AuthorController`         | `resource`                       | CRUD de autores                          |
| `Admin\CharacterController`      | `resource`                       | CRUD de personagens                      |
| `Admin\AnimeGenreController`     | `store`, `destroy`               | Associa/desassocia gênero a um anime     |
| `Admin\AnimeStudioController`    | `store`, `destroy`               | Associa/desassocia estúdio a um anime    |
| `Admin\AnimeCharacterController` | `store`, `destroy`               | Associa/desassocia personagem a um anime |
| `Admin\MangaGenreController`     | `store`, `destroy`               | Associa/desassocia gênero a um mangá     |
| `Admin\MangaAuthorController`    | `store`, `destroy`               | Associa/desassocia autor a um mangá      |
| `Admin\MangaCharacterController` | `store`, `destroy`               | Associa/desassocia personagem a um mangá |
