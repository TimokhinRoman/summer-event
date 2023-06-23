import {createRouter, createWebHashHistory} from "vue-router"
import EventView from "@/main/views/EventView";
import LobbyView from "@/main/views/LobbyView";
import EndView from "@/main/views/EndView";

const routes = [
  {
    path: "/event",
    name: "Event",
    component: EventView,
  },
  {
    path: "/lobby",
    name: "Lobby",
    component: LobbyView,
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
