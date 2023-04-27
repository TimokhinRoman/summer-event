<template>
  <router-link :to="{name: 'EventAdd'}" class="btn btn-success mb-2"
               role="button">Добавить событие
  </router-link>
  <div v-if="events !== null && events.length > 0" class="box">
    <div class="list-group">
      <router-link v-for="event in events" :key="event.id" :to="viewLink(event)"
                   class="list-group-item list-group-item-action d-flex gap-3 py-3">
        <div>
          <h6 class="mb-0">{{ event.name }}</h6>
          <p class="mb-0 opacity-75">{{ event.description }}</p>
        </div>
      </router-link>
    </div>
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
    viewLink(event) {
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
