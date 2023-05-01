const { defineConfig } = require("@vue/cli-service")
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "target/dist",
  assetsDir: "static",

  pages: {
    main: {
      entry: "src/main/main.js",
      template: "public/index.html",
      filename: "index.html",
      title: "Main"
    },
    admin: {
      entry: "src/admin/main.js",
      template: "public/admin.html",
      filename: "admin.html",
      title: "Admin"
    }
  }
})
