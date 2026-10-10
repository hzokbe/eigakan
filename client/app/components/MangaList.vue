<script lang="ts" setup>
const { mangas } = useMangas();
</script>

<template>
  <UPageList
    :ui="{
      base: 'flex flex-col gap-4',
    }"
  >
    <MangaCard
      v-for="manga in mangas"
      :id="manga.id"
      :key="manga.id"
      :chapters="manga.chapters"
      :image-source="manga.imageSource"
      :japanese-title="manga.japaneseTitle"
      :score="manga.score"
      :synopsis="manga.synopsis"
      :title="manga.title"
      :volumes="manga.volumes"
    />
  </UPageList>
</template>
