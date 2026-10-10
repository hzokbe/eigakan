<script lang="ts" setup>
const { animes } = useAnimes();
</script>

<template>
  <UPageList
    :ui="{
      base: 'flex flex-col gap-4',
    }"
  >
    <AnimeCard
      v-for="anime in animes"
      :id="anime.id"
      :key="anime.id"
      :episodes="anime.episodes"
      :image-source="anime.imageSource"
      :japanese-title="anime.japaneseTitle"
      :score="anime.score"
      :synopsis="anime.synopsis"
      :title="anime.title"
      :type="anime.type"
    />
  </UPageList>
</template>
