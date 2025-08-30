import { server } from "../server/config";
import type { MovieResponse } from "../types/MovieResponse";

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
