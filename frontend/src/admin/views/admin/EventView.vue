<template>
  <div v-if="event" class="w-full">
    <p class="text-4xl text-white font-bold">{{ event.name }}</p>
    <p class="text-color">{{ event.description }}</p>
    <p class="text-color">{{ event.active ? 'Активно' : 'Неактивно' }}</p>
    <p class="text-color">{{ event.status }}</p>
    <div class="flex flex-column">
      <Button v-if="!event.active" label="Сделать активным" class="my-1" @click="activateEvent"/>
      <Button v-if="event.active" label="Сделать неактивным" class="my-1" @click="deactivateEvent"/>
      <span class="p-buttonset my-1">
        <Button class="justify-content-center" @click="setEventStatus('PENDING')">
          <font-awesome-icon icon="fa-solid fa-users" size="xl"/>
        </Button>
        <Button class="justify-content-center" @click="setEventStatus('DRAW')">
          <font-awesome-icon icon="fa-solid fa-dice" size="xl"/>
        </Button>
        <Button class="justify-content-center" @click="setEventStatus('TASK_SELECTION')">
          <font-awesome-icon icon="fa-solid fa-map-location-dot" size="xl"/>
        </Button>
        <Button class="justify-content-center" @click="setEventStatus('TASK_IN_PROGRESS')">
          <font-awesome-icon icon="fa-solid fa-person-running" size="xl"/>
        </Button>
        <Button class="justify-content-center" @click="setEventStatus('ENDED')">
          <font-awesome-icon icon="fa-solid fa-flag-checkered" size="xl"/>
        </Button>
      </span>
      <div class="mt-4"/>
      <Button label="Список заданий" class="my-1" @click="showTaskList"/>
      <Button label="Список команд" class="my-1" @click="showTeamList"/>
      <Button label="Список участников" class="my-1" @click="showUserList"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'EventView',
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
      const eventId = this.$route.params.eventId;
      this.handleEventRequest(axios.get("/api/admin/events/" + eventId));
    },
    activateEvent() {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/activate`));
    },
    deactivateEvent() {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/deactivate`));
    },
    setEventStatus(status) {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/status?status=${status}`));
    },
    handleEventRequest(promise) {
      return promise
        .then(response => {
          this.event = response.data;
        })
    },
    showTaskList() {
      this.routeTo('EventTaskList');
    },
    showTeamList() {
      this.routeTo('EventTeamList');
    },
    showUserList() {
      this.routeTo('EventUserList');
    },
    routeTo(name) {
      this.$router.push({
        name: name,
        params: {
          eventId: this.event.id
        }
      })
    }
  }
}
</script>
