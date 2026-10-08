# Eigakan

Eigakan é uma aplicação web que permite que usuários explorem um catálogo de animes e mangás, montem suas listas
pessoais, avaliem títulos e marquem favoritos.

## Tecnologias

- **Server:** ASP.NET Core
- **Banco de dados:** PostgreSQL
- **Client:** Nuxt.js
- **Infraestrutura:** Docker

## Instalação

```shell
git clone https://github.com/hzokbe/eigakan.git

cd eigakan
```

Crie os arquivos de ambiente:

```shell
cp .env.example .env

cp client/.env.example client/.env
```

Revise os valores de ambos os arquivos antes de continuar.

## Execução

Suba os serviços de infraestrutura:

```shell
docker compose up -d
```

Inicie o servidor:

```shell
cd server

dotnet ef database update

dotnet run
```

Em outro terminal, inicie o client:

```shell
cd client

npm i

npm run dev
```

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
