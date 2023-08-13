const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  outputDir: "../src/main/resources/static",
  devServer: {
    port: 8081,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8090',
        changeOrigin: true
      }
    }
  }
})
