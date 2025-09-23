import { server } from "../server/config";
import type {
  MovieResponse,
  PaginatedMovieResponse,
} from "../types/MovieResponse";

export const getMovies = async (
  page: number,
  size: number
): Promise<PaginatedMovieResponse> => {
  const response = await server.get(`/movies?page=${page}&size=${size}`);

  const data = response.data;

  const content: MovieResponse[] = [];

  data.content.forEach((m: MovieResponse) => content.push(m));

  return {
    content: content,
    totalPages: data.totalPages,
    totalElements: data.totalElements,
    size: data.size,
    number: data.number,
  };
};

export const getAllMovies = async (): Promise<MovieResponse[]> => {
  const response = await server.get("/movies/all");

  const movies: MovieResponse[] = [];

  response.data.forEach((m: MovieResponse) => movies.push(m));

  return movies;
};

export const getMovieById = async (id: string): Promise<MovieResponse> => {
  const response = await server.get(`/movies/${id}`);

  const data = response.data;

  return { id: data.id, title: data.title, genre: data.genre };
};
