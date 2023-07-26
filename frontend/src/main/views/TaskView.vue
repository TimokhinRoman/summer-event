<template>
  <div class="container">
    <div class="content p-fluid">
      <template v-if="task">
        <div class="mb-auto text-2xl font-bold">
          {{ task.name }}
        </div>
      </template>
      <div v-else>
        loading...
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TaskView",
  data() {
    return {
      task: null,
      listeningToTaskEnd: null
    }
  },
  created() {
    this.loadTask();
  },
  methods: {
    loadTask() {
      axios.get(`/api/task/current`)
        .then(response => {
          this.task = response.data;

          this.listenToTaskEnd();
        })
    },
    listenToTaskEnd() {
      this.listeningToTaskEnd = setInterval(() => {
        axios.get("/api/task/current").then(response => {
          let task = response.data;
          if (!task) {
            this.cancelListeningToTaskEnd();
            this.$router.push("/map");
          }
        })
      }, 2000)
    },
    cancelListeningToTaskEnd() {
      if (this.listeningToTaskEnd) {
        clearInterval(this.listeningToTaskEnd);
        this.listeningToTaskEnd = null;
      }
    }
  }
}
</script>

<style scoped>

</style>
