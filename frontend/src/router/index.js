import { createRouter, createWebHashHistory } from "vue-router"
import HomeView from "../views/HomeView.vue"
import AdminView from "../views/admin/AdminView.vue"
import EventListView from "../views/admin/EventListView.vue"
import EventView from "../views/admin/EventView.vue"
import EventAddView from "../views/admin/EventAddView.vue"
import EventModifyView from "../views/admin/EventModifyView.vue"
import TaskView from "../views/admin/TaskView";
import TaskAddView from "../views/admin/TaskAddView";
import TaskModifyView from "../views/admin/TaskModifyView";

const routes = [
  {
    path: "/",
    component: HomeView
  },
  {
    path: "/admin",
    name: "Admin",
    component: AdminView,
    children: [
      {
        path: "events",
        name: "EventList",
        component: EventListView,
      },
      {
        path: "events/add",
        name: "EventAdd",
        component: EventAddView,
      },
      {
        path: "events/:eventId",
        name: "Event",
        component: EventView,
      },
      {
        path: "events/:eventId/modify",
        name: "EventModify",
        component: EventModifyView,
      },
      {
        path: "events/:eventId/tasks/add",
        name: "TaskAdd",
        component: TaskAddView,
      },
      {
        path: "events/:eventId/tasks/:taskId",
        name: "Task",
        component: TaskView,
      },
      {
        path: "events/:eventId/tasks/:taskId/modify",
        name: "TaskModify",
        component: TaskModifyView,
      }
    ]
  },
  {
    path: "/about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ "../views/AboutView.vue")
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
