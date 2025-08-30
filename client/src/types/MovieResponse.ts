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

export type MovieResponse = {
  id: string;

  title: string;

  genre: Genre;
};
