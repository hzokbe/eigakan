<script lang="ts" setup>
const api = useAPI();

const toast = useToast();

onMounted(async () => {
  try {
    const response = await api('/health');

    console.debug(response);

    toast.add({
      title: 'API is healthy',
      description: 'The API is up and running.',
      icon: 'i-lucide-circle-check',
      color: 'success',
    });
  } catch (e) {
    console.error(e);

    toast.add({
      title: 'Service unavailable',
      description: 'We could not connect to the API.',
      icon: 'i-lucide-circle-x',
      color: 'error',
    });
  }
});
</script>

<template>
  <UPageHero
    description="Discover, track, and rate your favorite anime and manga."
    title="Eigakan"
  />
</template>
