<template>
  <div class="content">
    <template v-if="task">
      <div>
        <span class="text-3xl font-bold">{{ task.name }}</span>
        <font-awesome-icon v-if="task.selected" icon="fa-solid fa-circle-check" size="xl" class="ml-2 vertical-align-sub"/>
        <font-awesome-icon v-if="task.completed" icon="fa-solid fa-flag-checkered" size="xl" class="ml-2 vertical-align-sub"/>
      </div>
      <div class="my-2" v-html="task.description"/>
    </template>
  </div>
  <div class="w-full">
    <template v-if="task">
      <Button class="my-1" v-if="!task.selected" label="Выбрать" @click="setSelected(true)"/>
      <Button class="my-1" v-if="task.selected" label="Развыбрать" @click="setSelected(false)"/>
      <Button class="my-1" v-if="!task.completed" label="Завершить" @click="setCompleted(true)"/>
      <Button class="my-1" v-if="task.completed" label="Развершить" @click="setCompleted(false)"/>
    </template>
    <div class="mt-4"/>
    <Button label="Назад" @click="back"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventTaskView",
  data() {
    return {
      task: null
    }
  },
  computed: {
    eventId() {
      return this.$route.params.eventId;
    },
    taskId() {
      return this.$route.params.taskId;
    }
  },
  created() {
    this.loadTask();
  },
  methods: {
    loadTask() {
      axios.get(`/api/admin/events/${this.eventId}/tasks/${this.taskId}`)
        .then(response => {
          console.log(response);
          this.task = response.data;
        })
    },
    setSelected(selected) {
      axios.post(`/api/admin/events/${this.eventId}/tasks/${this.taskId}/select?selected=${selected}`)
        .then(response => {
          this.task.selected = selected;
        })
    },
    setCompleted(completed) {
      axios.post(`/api/admin/events/${this.eventId}/tasks/${this.taskId}/complete?completed=${completed}`)
        .then(response => {
          this.task.completed = completed;
          this.task.selected = false;
        })
    },
    back() {
      this.$router.push({
        name: "EventTaskList",
        params: {
          eventId: this.eventId
        }
      })
    },
  }
}
</script>

<style scoped>

</style>
