<template>
  <div v-if="task && task.image" class="relative">
    <img :src="imageUrl" alt="" class="background">
  </div>
  <div class="container">
    <div class="content p-fluid">
      <template v-if="task">
        <div class="mb-auto">
          <p class="text-2xl font-bold">{{ task.name }}</p>
          <p>{{ task.description }}</p>
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
  computed: {
    imageUrl() {
      return require(`@/assets/img/${this.task.image}.png`);
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
.background {
  z-index: -1;
  position: fixed;
  bottom: 0;
  right: 0;
}
</style>
