<template>
  <div class="background"/>
  <div class="container">
    <div class="content p-fluid">
      <img src="@/assets/img/dice3.png" alt="">
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DrawView",
  data() {
    return {
      listeningToDrawEnd: null
    }
  },
  created() {
    this.listenToDrawEnd();
  },
  methods: {
    listenToDrawEnd() {
      this.listeningToDrawEnd = setInterval(() => {
        axios.get("/api/event").then(response => {
          let event = response.data;
          if (event) {
            switch (event.status) {
              case "DRAW":
                return;
              case "TASK_SELECTION":
                this.$router.push("/map");
                break;
              default:
                this.$router.push("/");
                break;
            }
          } else {
            this.$router.push("/");
          }
          this.cancelListeningToDrawEnd();
        })
      }, 2000)
    },
    cancelListeningToDrawEnd() {
      if (this.listeningToDrawEnd) {
        clearInterval(this.listeningToDrawEnd);
        this.listeningToDrawEnd = null;
      }
    }
  }
}
</script>

<style>
.draw-dice {
  font-size: 10em;
  color: var(--gray-100);

  animation-name: fa-shake;
  animation-delay: 2s;
  animation-direction: normal;
  animation-duration: 2s;
  animation-iteration-count: infinite;
  animation-timing-function: ease;
}
</style>
