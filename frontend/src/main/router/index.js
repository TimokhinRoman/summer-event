import {createRouter, createWebHashHistory} from "vue-router"
import LobbyView from "@/main/views/LobbyView";
import MapView from "@/main/views/MapView";
import EndView from "@/main/views/EndView";

const routes = [
  {
    path: "/lobby",
    name: "Lobby",
    component: LobbyView,
  },
  {
    path: "/map",
    name: "Map",
    component: MapView,
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

export default router
