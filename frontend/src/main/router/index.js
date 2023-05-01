import {createRouter, createWebHashHistory} from "vue-router"
import MainView from "@/main/views/MainView";

const routes = [
  {
    path: "/",
    name: "Main",
    component: MainView,
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
