<template>
  <div v-if="event" class="w-full">
    <p class="text-4xl text-white font-bold">{{ event.name }}</p>
    <p class="text-color">{{ event.description }}</p>
    <p class="text-color">{{ event.active ? 'Активно' : 'Неактивно' }}</p>
    <p class="text-color">{{ event.status }}</p>
    <div class="flex flex-column">
      <Button v-if="!event.active" label="Сделать активным" class="my-1" @click="activateEvent"/>
      <Button v-if="event.active" label="Сделать неактивным" class="my-1" @click="deactivateEvent"/>
      <Button label="Подготовка" class="my-1" @click="setPending"/>
      <Button label="Начать" class="my-1" @click="setStarted"/>
      <Button label="Завершить" class="my-1" @click="setEnded"/>
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
    setPending() {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/pending`));
    },
    setStarted() {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/started`));
    },
    setEnded() {
      this.handleEventRequest(axios.post(`/api/admin/events/${this.event.id}/ended`));
    },
    handleEventRequest(promise) {
      return promise
        .then(response => {
          console.log(response);
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
