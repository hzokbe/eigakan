<script lang="ts" setup>
const route = useRoute();

const router = useRouter();

const toast = useToast();

onMounted(() => {
  if (route.query.error === 'not-found') {
    if (import.meta.client) {
      toast.add({
        id: 'anime-not-found',
        title: 'Anime not found',
        description:
          'The anime you tried to access does not exist or was removed.',
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
      description="Discover the highest-rated anime, explore their stories, and find your next favorite to watch."
      title="Top animes"
    >
      <AnimeList />
    </UPageSection>
  </UContainer>
</template>
