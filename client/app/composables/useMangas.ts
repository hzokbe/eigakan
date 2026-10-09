export const useMangas = () => {
  const api = useAPI();

  const {
    data: mangas,
    status,
    error,
    refresh,
  } = useAsyncData('mangas-list', () => api<Manga[]>('/mangas'), {
    default: () => [],
  });

  return { mangas, status, error, refresh };
};
