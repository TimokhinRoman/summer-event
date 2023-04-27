<template>
  <div v-if="task" class="card box">
    <div class="card-body">
      <h5 class="card-title">{{ task.name }}</h5>
      <h6 class="card-subtitle mb-2 text-muted">{{ getTypeView(task.type) }}</h6>
      <p class="card-text">
        {{ task.description }}

        <span v-if="isQuestion(task.type)" class="d-block mt-2">
          <b>Ответ:</b> {{ task.answer }}
        </span>
      </p>
      <router-link :to="{name: 'Event', params: {eventId: task.eventId}}" class="btn btn-primary me-2" role="button">
        Назад
      </router-link>
      <router-link :to="{name: 'TaskModify', params: {eventId: task.eventId, taskId: task.id}}"
                   class="btn btn-success me-2" role="button">
        Редактировать
      </router-link>
      <button type="button" class="btn btn-danger" @click="deleteTask">Удалить</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "TaskView",
  data() {
    return {
      task: null
    }
  },
  created() {
    this.loadTask();
  },
  methods: {
    loadTask() {
      const eventId = this.$route.params.eventId;
      const taskId = this.$route.params.taskId;
      axios.get(`/api/admin/events/${eventId}/tasks/${taskId}`)
        .then(response => {
          console.log(response);
          this.task = response.data;
        })
    },
    getTypeView(type) {
      if (this.isAction(type)) {
        return "Выполните действие:"
      } else if (this.isQuestion(type)) {
        return "Ответьте на вопрос:"
      } else {
        return `???${type}???`
      }
    },
    isAction(type) {
      return type === "ACTION";
    },
    isQuestion(type) {
      return type === "QUESTION";
    },
    deleteTask() {
      axios.delete(`/api/admin/events/${this.task.eventId}/tasks/${this.task.id}`)
        .then(response => {
          console.log(response);
          router.push({name: "Event", params: {
            eventId: this.task.eventId
          }})
        })
    }
  }
}
</script>

<style scoped>

</style>
