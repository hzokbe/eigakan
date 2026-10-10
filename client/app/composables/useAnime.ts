export const useAnime = (id: MaybeRefOrGetter<string>) => {
  const api = useAPI();

  return useAsyncData(
    () => `anime-${toValue(id)}`,
    () => api<Anime>(`/animes/${toValue(id)}`),
    { watch: [() => toValue(id)] },
  );
};
