import {createRouter, createWebHashHistory} from "vue-router"
import AdminView from "../views/admin/AdminView.vue"
import EventListView from "../views/admin/EventListView.vue"
import EventView from "../views/admin/EventView.vue"
import EventTaskListView from "../views/admin/EventTaskListView.vue"
import EventTaskView from "../views/admin/EventTaskView.vue"
import EventTeamListView from "../views/admin/EventTeamListView.vue"
import EventTeamView from "../views/admin/EventTeamView.vue"
import EventUserListView from "../views/admin/EventUserListView.vue"
import TeamListView from "../views/admin/TeamListView.vue"
import UserListView from "../views/admin/UserListView.vue"

/*import EventAddView from "../views/admin/EventAddView.vue"
import EventModifyView from "../views/admin/EventModifyView.vue"
import TaskView from "../views/admin/TaskView";
import TaskAddView from "../views/admin/TaskAddView";
import TaskModifyView from "../views/admin/TaskModifyView";*/

const routes = [
  {
    path: "/",
    name: "Admin",
    component: AdminView,
    children: [
      {
        path: "events",
        name: "EventList",
        component: EventListView,
      },
      /*{
        path: "events/add",
        name: "EventAdd",
        component: EventAddView,
      },*/
      {
        path: "events/:eventId",
        name: "Event",
        component: EventView,
      },
      {
        path: "events/:eventId/tasks",
        name: "EventTaskList",
        component: EventTaskListView,
      },
      {
        path: "events/:eventId/tasks/:taskId",
        name: "EventTask",
        component: EventTaskView,
      },
      {
        path: "events/:eventId/teams",
        name: "EventTeamList",
        component: EventTeamListView,
      },
      {
        path: "events/:eventId/teams/:teamId",
        name: "EventTeam",
        component: EventTeamView,
      },
      {
        path: "events/:eventId/users",
        name: "EventUserList",
        component: EventUserListView,
      },
      /*{
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
      }*/
      {
        path: "teams",
        name: "TeamList",
        component: TeamListView,
      },
      {
        path: "users",
        name: "UserList",
        component: UserListView,
      },
    ]
  }
]

const router = createRouter({
  base: "/admin",
  history: createWebHashHistory(),
  routes
})

export default router
