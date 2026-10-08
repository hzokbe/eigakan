export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  modules: ['@nuxt/ui', '@nuxt/eslint'],
  css: ['~/assets/css/main.css'],
  ssr: true,
  runtimeConfig: {
    public: {
      apiURL: '',
      appURL: '',
    },
  },
  typescript: {
    typeCheck: true,
  },
});
