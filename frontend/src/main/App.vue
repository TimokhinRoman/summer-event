<template>
  <div class="container">
    <div class="content p-fluid">
      <router-view/>
    </div>
  </div>
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
      task: null
    }
  },
  methods: {
    loadBriefing() {
      axios.get("/api/briefing")
        .then(response => {
          console.log(response);
          this.user = response.data.user;
          this.event = response.data.event;
          this.task = response.data.task;
        })
        .finally(() => {
          this.$store.commit("init");

          if (!this.event) {
            console.log("There is no active event.");
          } else {
            switch (this.event.status) {
              case "CREATED":
                console.log("There is no active event.");
                break;
              case "PENDING":
                this.showLobby();
                break;
              case "STARTED":
                this.showEvent();
                break;
              case "ENDED":
                this.showEnd();
                break;
            }
          }
        })
    },
    showLobby() {
      this.$router.push("/lobby");
    },
    showEvent() {
      if (this.task) {
        this.$router.push("/task/current");
      } else {
        this.$router.push("/map");
      }
    },
    showEnd() {
      this.$router.push("/end");
    }
  }
}
</script>
