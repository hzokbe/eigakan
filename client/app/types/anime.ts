export type AnimeType = 'TV' | 'Movie' | 'OVA' | 'ONA' | 'TVSpecial';

export type AnimeStatus = 'FinishedAiring' | 'CurrentlyAiring' | 'NotYetAired';

export interface Anime {
  id: string;

  title: string;

  japaneseTitle: string | null;

  synopsis: string | null;

  type: AnimeType;

  episodes: number | null;

  status: AnimeStatus;

  airedFrom: string | null;

  airedTo: string | null;

  score: number | null;

  imageSource: string | null;
}
