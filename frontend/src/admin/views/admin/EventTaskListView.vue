<template>
  <div class="content">
    <p class="text-3xl font-bold">Задания</p>
    <template v-if="tasks && tasks.length > 0">
      <ul class="list-none p-0">
        <li v-for="task in tasks" :key="task.id" class="my-2">
          <router-link :to="taskLink(task)" class="no-underline text-white">
            <span class="text-xl">{{ task.name }}</span>
          </router-link>
          <font-awesome-icon v-if="task.selected" icon="fa-solid fa-circle-check" size="xl" class="ml-2 vertical-align-sub"/>
          <font-awesome-icon v-if="task.completed" icon="fa-solid fa-flag-checkered" size="xl" class="ml-2 vertical-align-sub"/>
        </li>
      </ul>
    </template>
    <div v-else>пусто</div>
  </div>
  <div class="w-full">
    <Button label="Назад" @click="back"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventTaskListView",
  data() {
    return {
      tasks: null
    }
  },
  computed: {
    eventId() {
      return this.$route.params.eventId;
    }
  },
  created() {
    this.loadTasks();
  },
  methods: {
    loadTasks() {
      axios.get(`/api/admin/events/${this.eventId}/tasks`)
        .then(response => {
          console.log(response);
          this.tasks = response.data;
        })
    },
    taskLink(task) {
      return {
        name: "EventTask",
        params: {
          eventId: this.eventId,
          taskId: task.id
        }
      }
    },
    back() {
      this.$router.push({
        name: "Event",
        params: {
          eventId: this.eventId
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
