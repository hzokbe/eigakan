<script lang="ts" setup>
defineProps<{
  text: string;
}>();
</script>

<template>
  <UTooltip :delay-duration="500" :text="text">
    <slot />
  </UTooltip>
</template>
