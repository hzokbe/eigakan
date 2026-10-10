<script lang="ts" setup>
const route = useRoute();

const router = useRouter();

const toast = useToast();

onMounted(() => {
  if (route.query.error === 'not-found') {
    if (import.meta.client) {
      toast.add({
        id: 'manga-not-found',
        title: 'Manga not found',
        description:
          'The manga you tried to access does not exist or was removed.',
        icon: 'i-lucide-circle-alert',
        color: 'error',
      });
    }

    router.replace({ query: {} });
  }
});
</script>

<template>
  <UContainer>
    <UPageSection
      description="Discover the highest-rated mangas, explore their stories, and find your next favorite to read."
      title="Top mangas"
    >
      <MangaList />
    </UPageSection>
  </UContainer>
</template>
