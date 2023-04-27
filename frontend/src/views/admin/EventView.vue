<template>
  <div v-if="event" class="card box">
    <div class="card-body">
      <h4 class="card-title mb-2">{{ event.name }}</h4>
      <p class="card-text">{{ event.description }}</p>

      <div>
        <router-link :to="{name: 'EventModify', params: event.id}" class="btn btn-primary me-2" role="button">
          Редактировать
        </router-link>
        <router-link :to="{name: 'TaskAdd', params: event.id}" class="btn btn-success" role="button">
          Добавить задание
        </router-link>
      </div>

      <div class="mt-4">
        <h5>Задания</h5>
        <div class="list-group">
          <router-link v-for="task in event.tasks" :key="task.id" :to=taskViewLink(task)
                       class="list-group-item list-group-item-action">
            {{ task.name }}
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'EventView',
  data() {
    return {
      event: null
    }
  },
  created() {
    this.loadEvent();
  },
  methods: {
    loadEvent() {
      const eventId = this.$route.params.eventId;
      axios.get("/api/admin/events/" + eventId)
        .then(response => {
          console.log(response);
          this.event = response.data;
        })
    },
    taskViewLink(task) {
      return {
        name: "Task",
        params: {
          eventId: task.eventId,
          taskId: task.id
        }
      };
    }
  }
}
</script>
