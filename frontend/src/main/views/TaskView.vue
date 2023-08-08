<template>
  <div class="container">
    <div class="content p-fluid">
      <template v-if="task">
        <div class="mb-auto">
          <p class="text-2xl font-bold">{{ task.name }}</p>
          <div class="my-4 text-left" v-html="task.description"/>
        </div>
        <div v-if="task.image" class="img-footer">
          <img :src="imageUrl" alt="" class="ml-auto">
        </div>
        <Button label="К карте" class="mt-2 text-xl font-medium"
                @click="showMap"/>
      </template>
      <div v-else>
        <span class="text-2xl">загрузка...</span>
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
        axios.get("/api/event").then(response => {
          let event = response.data;
          if (event) {
            switch (event.status) {
              case "TASK_IN_PROGRESS":
                return;
              case "DRAW":
                this.$router.push("/draw");
                break;
              default:
                this.$router.push("/");
                break;
            }
          } else {
            this.$router.push("/");
          }
          this.cancelListeningToTaskEnd();
        })
      }, 2000)
    },
    cancelListeningToTaskEnd() {
      if (this.listeningToTaskEnd) {
        clearInterval(this.listeningToTaskEnd);
        this.listeningToTaskEnd = null;
      }
    },
    showMap() {
      this.$router.push("/map");
    }
  }
}
</script>

<style scoped>
.img-footer {
  width: 100%;
  max-width: calc(545px - 4rem);
  margin-top: auto;
  display: flex;
}
</style>
