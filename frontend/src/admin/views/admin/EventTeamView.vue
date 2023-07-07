<template>
  <div class="content">
    <template v-if="team">
      <div>
        <span class="text-3xl font-bold">{{ team.name }}</span>
        <font-awesome-icon v-if="team.chooser" icon="fa-solid fa-dice" size="xl" class="ml-2 vertical-align-sub"/>
      </div>
      <p class="text-xl">Участники</p>
      <ul v-if="team.users" class="list-none p-0 m-0">
        <li v-for="user in team.users" :key="user.id">
          <span class="text-2xl font-medium">
            {{ user.name }}
          </span>
          <font-awesome-icon v-if="isCaptain(user)" icon="fa-solid fa-crown" size="xl" class="ml-2 vertical-align-sub"/>
        </li>
      </ul>
    </template>
  </div>
  <div class="w-full">
    <template v-if="team">
      <Button class="my-1" v-if="!team.chooser" label="Дать право выбора" @click="choose"/>
      <Button class="my-1" v-if="team.chooser" label="Забрать право выбора" @click="unchoose"/>

      <template v-if="event">
        <Button class="my-1" v-if="event.status === 'PENDING'" label="Исключить" @click="kick"/>
      </template>
    </template>
    <div class="mt-4"/>
    <Button label="Назад" @click="back"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventTeamView",
  data() {
    return {
      event: null,
      team: null
    }
  },
  computed: {
    eventId() {
      return this.$route.params.eventId;
    },
    teamId() {
      return this.$route.params.teamId;
    }
  },
  created() {
    this.loadTeam();
  },
  methods: {
    loadTeam() {
      axios.get(`/api/admin/events/${this.eventId}/teams/${this.teamId}`)
        .then(response => {
          console.log(response);
          this.event = response.data.event;
          this.team = response.data.team;
        })
    },
    kick() {
      axios.delete(`/api/admin/events/${this.eventId}/teams/${this.teamId}`)
        .then(response => {
          console.log(response);
          this.back();
        })
    },
    choose() {
      axios.post(`/api/admin/events/${this.eventId}/teams/${this.teamId}/choose`)
        .then(response => {
          console.log(response);
          this.team.chooser = true;
        })
    },
    unchoose() {
      axios.delete(`/api/admin/events/${this.eventId}/teams/${this.teamId}/choose`)
        .then(response => {
          console.log(response);
          this.team.chooser = false;
        })
    },
    back() {
      this.$router.push({
        name: 'EventTeamList',
        params: {
          eventId: this.eventId
        }
      })
    },
    isCaptain(user) {
      return this.team && this.team.captain && this.team.captain.id === user.id;
    }
  }
}
</script>

<style scoped>

</style>
