<template>
  <template v-if="!loading">
    <template v-if="!event || event.status === 'CREATED'">
      <div class="background"/>
      <div class="container">
        <div class="content p-fluid">
          <p class="text-xl shadow-outline">Здесь скоро что-то будет...</p>
        </div>
      </div>
    </template>
    <router-view v-else/>
  </template>
</template>

<script>
import axios from "axios";

export default {
  created() {
    this.loadBriefing();
  },
  data() {
    return {
      user: null,
      event: null,
      task: null,
      loading: true
    }
  },
  methods: {
    loadBriefing() {
      axios.get("/api/briefing")
        .then(response => {
          this.user = response.data.user;
          this.event = response.data.event;
          this.task = response.data.task;
        })
        .finally(() => {
          this.loading = false;

          if (!this.event || this.event.status === 'CREATED') return;

          this.$store.commit("init");

          switch (this.event.status) {
            case "PENDING":
              this.showLobby();
              break;
            case "DRAW":
              this.showDraw();
              break;
            case "TASK_SELECTION":
              this.showMap();
              break;
            case "TASK_IN_PROGRESS":
              this.showTask();
              break;
            case "ENDED":
              this.showEnd();
              break;
          }
        })
    },
    showLobby() {
      this.$router.push("/lobby");
    },
    showDraw() {
      this.$router.push("/draw");
    },
    showMap() {
      this.$router.push("/map");
    },
    showTask() {
      this.$router.push("/task");
    },
    showEnd() {
      this.$router.push("/end");
    }
  }
}
</script>

<style>
@import "../assets/app.css";
</style>
