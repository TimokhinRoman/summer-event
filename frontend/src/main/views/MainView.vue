<template>
  <div class="d-flex h-100 text-center text-bg-dark">
    <template v-if="event">
      <header>
        <div>
          <nav></nav>
        </div>
      </header>
      <main>
        <h1>{{ event.name }}</h1>
        <p>{{ event.description }}</p>
      </main>
    </template>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MainView",
  data() {
    return {
      event: null
    }
  },
  created() {
    this.loadEvent();
  },
  methods: {
    loadEvent() {
      axios.get("/api/event")
        .then(response => {
          console.log(response);
          this.event = response.data;
        })
    },
  }
}
</script>

<style scoped>
html {

}
</style>
