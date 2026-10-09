export const useAnimes = () => {
  const api = useAPI();

  const {
    data: animes,
    status,
    error,
    refresh,
  } = useAsyncData('animes-list', () => api<Anime[]>('/animes'), {
    default: () => [],
  });

  return { animes, status, error, refresh };
};
