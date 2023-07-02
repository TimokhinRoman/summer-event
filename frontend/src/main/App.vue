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
  methods: {
    loadBriefing() {
      axios.get("/api/briefing")
        .then(response => {
          this.$store.commit("event", response.data.event);
          this.$store.commit("user", response.data.user);
        })
        .finally(() => {
          const event = this.$store.state.event;
          if (!event) {
            console.log("There is no active event.");
          } else {
            switch (event.status) {
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
      this.$router.push("/event");
    },
    showEnd() {
      this.$router.push("/end");
    }
  }
}
</script>
