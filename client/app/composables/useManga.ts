export const useManga = (id: MaybeRefOrGetter<string>) => {
  const api = useAPI();

  return useAsyncData(
    () => `manga-${toValue(id)}`,
    () => api<Manga>(`/mangas/${toValue(id)}`),
    { watch: [() => toValue(id)] },
  );
};
