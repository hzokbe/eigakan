<template>
  <UHeader :toggle="false" :ui="{ title: 'items-center gap-2' }" to="/">
    <template #title>
      <UIcon class="size-5 text-primary" name="i-lucide-clapperboard" />
      Eigakan
    </template>
    <template #right>
      <UColorModeButton />
    </template>
  </UHeader>
</template>
