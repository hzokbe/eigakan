<script lang="ts" setup>
import type { NavigationMenuItem } from '@nuxt/ui/components/NavigationMenu.vue';

const route = useRoute();

const items = computed<NavigationMenuItem[]>(() => [
  {
    label: 'Animes',
    to: '/animes',
    active: route.path.startsWith('/animes'),
    icon: 'i-lucide-tv-minimal-play',
  },
  {
    label: 'Mangas',
    to: '/mangas',
    active: route.path.startsWith('/mangas'),
    icon: 'i-lucide-book',
  },
]);
</script>

<template>
  <UHeader :ui="{ title: 'items-center gap-2' }" to="/">
    <template #title>
      <UIcon class="size-5 text-primary" name="i-lucide-clapperboard" />
      Eigakan
    </template>
    <UNavigationMenu :items="items" />
    <template #body>
      <UNavigationMenu :items="items" orientation="vertical" />
    </template>
    <template #right>
      <UColorModeButton />
    </template>
  </UHeader>
</template>
