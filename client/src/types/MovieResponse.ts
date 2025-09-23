export type Genre =
  | "ACTION"
  | "ADVENTURE"
  | "ANIMATION"
  | "COMEDY"
  | "CRIME"
  | "DOCUMENTARY"
  | "DRAMA"
  | "FANTASY"
  | "HISTORICAL"
  | "HORROR"
  | "MUSICAL"
  | "MYSTERY"
  | "ROMANCE"
  | "SCIENCE_FICTION"
  | "THRILLER"
  | "WESTERN"
  | "BIOGRAPHY"
  | "FAMILY"
  | "WAR"
  | "SPORT";

export type PaginatedMovieResponse = {
  content: MovieResponse[];

  totalPages: number;

  number: number;

  size: number;

  totalElements: number;

  first?: boolean;

  last?: boolean;
};

export type MovieResponse = {
  id: string;

  title: string;

  genre: Genre;
};
