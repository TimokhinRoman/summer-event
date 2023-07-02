<template>
  <!--div v-if="event">
    <header>
      <div>
        <nav></nav>
      </div>
    </header>
    <main>
      <h1>{{ event.name }}</h1>
      <p>{{ event.description }}</p>
    </main>
  </div-->
</template>

<script>
import axios from "axios";

export default {
  name: "EventView",
  computed: {
    event() {
      return this.$store.state.event
    }
  },
  created() {
    //this.loadEvent();
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
