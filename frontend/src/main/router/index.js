import {createRouter, createWebHashHistory} from "vue-router"
import App from "@/main/App"
import LobbyView from "@/main/views/LobbyView";
import DrawView from "@/main/views/DrawView";
import MapView from "@/main/views/MapView";
import TaskView from "@/main/views/TaskView";
import EndView from "@/main/views/EndView";
import store from "../store/index"

const routes = [
  {
    path: "/",
    name: "App",
    component: App,
  },
  {
    path: "/lobby",
    name: "Lobby",
    component: LobbyView,
  },
  {
    path: "/draw",
    name: "Draw",
    component: DrawView,
  },
  {
    path: "/map",
    name: "Map",
    component: MapView,
  },
  {
    path: "/task",
    name: "Task",
    component: TaskView,
  },
  {
    path: "/end",
    name: "End",
    component: EndView,
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from) => {
  if (!store.state.inited) {
    return false
  }
})

export default router
