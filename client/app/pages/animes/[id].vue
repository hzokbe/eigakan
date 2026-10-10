<script lang="ts" setup>
const route = useRoute();

const { data: anime, error } = await useAnime(() => route.params.id as string);

if (error.value || !anime.value) {
  await navigateTo(
    { path: '/animes', query: { error: 'not-found' } },
    { replace: true },
  );
}
</script>

<template>
  <UContainer v-if="anime">
    <UPageBody class="flex flex-col gap-4">
      <UPageSection
        :description="anime.japaneseTitle ?? 'No synopsis'"
        :title="anime.title"
      >
        <AnimeDetailsCard
          :id="anime.id"
          :aired-from="anime.airedFrom"
          :aired-to="anime.airedTo"
          :episodes="anime.episodes"
          :image-source="anime.imageSource"
          :japanese-title="anime.japaneseTitle"
          :score="anime.score"
          :status="anime.status"
          :synopsis="anime.synopsis"
          :title="anime.title"
          :type="anime.type"
        />
      </UPageSection>
    </UPageBody>
  </UContainer>
</template>
