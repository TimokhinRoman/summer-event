const { defineConfig } = require("@vue/cli-service")
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "target/dist",
  assetsDir: "static",

  pages: {
    login: {
      entry: "src/login/main.js",
      template: "public/login.html",
      filename: "login.html",
      title: "Login"
    },
    register: {
      entry: "src/register/main.js",
      template: "public/register.html",
      filename: "register.html",
      title: "Register"
    },
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
