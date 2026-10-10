<script lang="ts" setup>
defineProps<{
  id: string;

  title: string;

  japaneseTitle: string | null;

  synopsis: string | null;

  chapters: number | null;

  volumes: number | null;

  score: number | null;

  imageSource: string | null;
}>();
</script>

<template>
  <UCard
    :ui="{
      header: 'flex gap-4',
      footer: 'flex gap-2',
    }"
  >
    <template #header>
      <NuxtLink :to="`/mangas/${id}`">
        <img
          :alt="title"
          :src="imageSource ?? ''"
          class="w-24 object-contain hover:brightness-70 transition-all duration-250 ease-in-out rounded-sm"
          loading="lazy"
        />
      </NuxtLink>
      <div class="flex flex-1 flex-col gap-2">
        <NuxtLink
          :to="`/mangas/${id}`"
          class="flex flex-col gap-1 hover:brightness-70 transition-all duration-250 ease-in-out w-fit"
        >
          <h3 class="w-fit text-xl font-bold">
            {{ title }}
          </h3>
          <h4 class="w-fit text-xs font-bold text-muted">
            {{ japaneseTitle }}
          </h4>
        </NuxtLink>
        <p
          v-if="synopsis"
          class="line-clamp-2 text-justify text-muted font-normal"
        >
          {{ synopsis }}
        </p>
        <p v-else class="text-muted font-normal">No synopsis</p>
      </div>
    </template>
    <template #footer>
      <div class="flex flex-wrap gap-2">
        <AppTooltip text="Score">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-star"
            variant="outline"
          >
            {{ score ?? 0.0 }}
          </UBadge>
        </AppTooltip>
        <AppTooltip text="Chapters">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-book-open"
            variant="outline"
          >
            {{ chapters ?? 0 }}
          </UBadge>
        </AppTooltip>
        <AppTooltip text="Volumes">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-library"
            variant="outline"
          >
            {{ volumes ?? 0 }}
          </UBadge>
        </AppTooltip>
      </div>
    </template>
  </UCard>
</template>
