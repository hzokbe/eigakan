<template>
  <USeparator class="h-px" />
  <UFooter>
    <template #left>
      <p class="text-muted text-sm">
        © {{ new Date().getFullYear() }} Eigakan.
      </p>
    </template>
    <template #right>
      <UButton
        aria-label="Eigakan on GitHub"
        color="neutral"
        icon="i-simple-icons-github"
        target="_blank"
        to="https://github.com/hzokbe/eigakan"
        variant="ghost"
      />
    </template>
  </UFooter>
</template>
