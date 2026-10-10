<script lang="ts" setup>
const route = useRoute();

const { data: manga, error } = await useManga(() => route.params.id as string);

if (error.value || !manga.value) {
  await navigateTo(
    { path: '/mangas', query: { error: 'not-found' } },
    { replace: true },
  );
}
</script>

<template>
  <UContainer v-if="manga">
    <UPageBody class="flex flex-col gap-4">
      <UPageSection
        :description="manga.japaneseTitle ?? 'No synopsis'"
        :title="manga.title"
      >
        <MangaDetailsCard
          :id="manga.id"
          :chapters="manga.chapters"
          :image-source="manga.imageSource"
          :japanese-title="manga.japaneseTitle"
          :published-from="manga.publishedFrom"
          :published-to="manga.publishedTo"
          :score="manga.score"
          :status="manga.status"
          :synopsis="manga.synopsis"
          :title="manga.title"
          :volumes="manga.volumes"
        />
      </UPageSection>
    </UPageBody>
  </UContainer>
</template>
