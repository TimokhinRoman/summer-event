<template>
  <form @submit.prevent="saveTask">
    <div class="card box">
      <div class="card-body">
        <h5 class="card-title mb-2">Добавление задания:</h5>
        <select-field v-model="task.type" name="type" label="Тип" :items="types" :selected-item="task.type"/>
        <input-text-field v-model="task.name" name="name" label="Название" placeholder="Название"/>
        <input-text-area-field v-model="task.description" name="description" label="Описание" placeholder="Описание"/>
        <input-text-field v-if="task.type === 'QUESTION'" v-model="task.answer" name="answer" label="Ответ"
                          placeholder="Ответ"/>
        <div>
          <router-link :to="{name: 'Event', params: {eventId: task.eventId}}" class="btn btn-primary me-2"
                       role="button">Назад
          </router-link>
          <button class="btn btn-success">Сохранить</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>

import InputTextField from "@/admin/components/InputTextField";
import InputTextAreaField from "@/admin/components/InputTextAreaField";
import axios from "axios";
import router from "@/admin/router";
import SelectField from "@/admin/components/SelectField";

export default {
  name: "TaskAddView",
  components: {
    SelectField,
    InputTextField, InputTextAreaField
  },
  data() {
    return {
      task: {
        eventId: this.$route.params.eventId,
        type: "ACTION",
        name: null,
        description: null,
        answer: null
      },
      types: [
        {
          id: "ACTION",
          view: "Выполните действие"
        },
        {
          id: "QUESTION",
          view: "Ответьте на вопрос"
        }
      ]
    }
  },
  methods: {
    saveTask() {
      console.log("Saving task...", this.task);
      axios.post(`/api/admin/events/${this.task.eventId}/tasks`, this.task)
        .then(response => {
          console.log(response);
          router.push({
            name: "Event", params: {
              eventId: this.task.eventId,
            }
          })
        })
    }
  }
}
</script>

<style scoped>

</style>
