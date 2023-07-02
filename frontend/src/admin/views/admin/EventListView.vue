<template>
  <p class="text-3xl font-bold">События</p>

  <div v-if="events && events.length > 0" class="w-full">
    <router-link v-for="event in events" :key="event.id" :to="eventLink(event)"
                 class="no-underline">
      <div class="w-full border-1 border-x-none py-3">
        <div class="text-3xl text-white font-bold">{{ event.name }}</div>
        <div class="text-color">{{ event.description }}</div>
        <div class="text-color">{{ event.active ? 'Активно' : 'Неактивно' }}</div>
        <div class="text-color">{{ event.status }}</div>
      </div>
    </router-link>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventListView",
  data() {
    return {
      events: null
    }
  },
  created() {
    this.loadEvents();
  },
  methods: {
    loadEvents() {
      axios.get("/api/admin/events")
        .then(response => {
          console.log(response);
          this.events = response.data;
        })
    },
    eventLink(event) {
      return {
        name: "Event",
        params: {
          eventId: event.id
        }
      }
    }
  }
}
</script>

<style>

</style>
