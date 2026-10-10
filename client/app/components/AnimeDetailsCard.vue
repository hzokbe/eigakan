<script lang="ts" setup>
const props = defineProps<{
  id: string;

  title: string;

  japaneseTitle: string | null;

  synopsis: string | null;

  type: AnimeType;

  status: AnimeStatus;

  episodes: number | null;

  score: number | null;

  imageSource: string | null;

  airedFrom: string | null;

  airedTo: string | null;
}>();

const dateFormatter = new Intl.DateTimeFormat('pt-BR', { dateStyle: 'medium' });

function formatDate(value: string | null | undefined): string {
  if (!value) {
    return '?';
  }

  const date = new Date(value);

  return Number.isNaN(date.getTime()) ? '?' : dateFormatter.format(date);
}

const airedRange = computed(() => {
  return `${formatDate(props.airedFrom)} - ${formatDate(props.airedTo)}`;
});

function formatStatus(value: string): string {
  return value.replace(/([a-z])([A-Z])/g, '$1 $2');
}
</script>

<template>
  <UCard
    :ui="{
      header: 'flex gap-4',
      footer: 'flex gap-2',
    }"
  >
    <template #header>
      <img
        :alt="title"
        :src="imageSource ?? ''"
        class="w-48 object-contain hover:brightness-70 transition-all duration-250 ease-in-out rounded-sm"
        loading="lazy"
      />
      <div class="flex flex-1 flex-col gap-2 overflow-y-scroll">
        <p v-if="synopsis" class="text-justify text-muted font-normal max-h-48">
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
        <AppTooltip text="Episodes">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-list-video"
            variant="outline"
          >
            {{ episodes ?? 0 }}
          </UBadge>
        </AppTooltip>
        <AppTooltip text="Type">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-clapperboard"
            variant="outline"
          >
            {{ type }}
          </UBadge>
        </AppTooltip>
        <AppTooltip text="Aired">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-calendar"
            variant="outline"
          >
            {{ airedRange }}
          </UBadge>
        </AppTooltip>
        <AppTooltip text="Status">
          <UBadge
            class="select-none"
            color="neutral"
            icon="i-lucide-info"
            variant="outline"
          >
            {{ formatStatus(status) }}
          </UBadge>
        </AppTooltip>
      </div>
    </template>
  </UCard>
</template>
