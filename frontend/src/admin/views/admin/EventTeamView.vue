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

      <p class="text-xl mt-4">Очки</p>
      <ul v-if="team.scores" class="list-none p-0 m-0 w-full">
        <li class="flex flex-row my-1" v-for="score in team.scores" :key="score.task.id">
          <div class="flex flex-1 align-items-center">
            <div class="flex-1 text-left">{{ score.task.name }}</div>
            <div>{{ score.score }}</div>
          </div>
          <div class="flex flex-row ml-2">
            <InputText v-model="score.scoreDelta" type="number" size="small" style="width: 55px"/>
            <Button class="justify-content-center ml-1" @click="saveTaskScore(score)">
              <font-awesome-icon icon="fa-solid fa-floppy-disk" size="xl"/>
            </Button>
          </div>
        </li>
      </ul>
    </template>
  </div>
  <div class="w-full mt-4">
    <template v-if="team">
      <Button class="my-1" v-if="!team.chooser" label="Дать право выбора" @click="choose"/>
      <Button class="my-1" v-if="team.chooser" label="Забрать право выбора" @click="unchoose"/>

      <template v-if="event">
        <Button class="my-1" v-if="event.status === 'PENDING'" label="Удалить" @click="remove"/>
      </template>
    </template>
    <div class="mt-1"/>
    <Button label="Назад" @click="back"/>
  </div>
</template>

<script>
import axios from "axios";
import InputText from "primevue/inputtext";

export default {
  name: "EventTeamView",
  components: {InputText},
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
          this.event = response.data.event;
          this.team = response.data.team;
        })
    },
    remove() {
      axios.delete(`/api/admin/events/${this.eventId}/teams/${this.teamId}`)
        .then(_ => {
          this.back();
        })
    },
    choose() {
      axios.post(`/api/admin/events/${this.eventId}/teams/${this.teamId}/choose`)
        .then(_ => {
          this.team.chooser = true;
        })
    },
    unchoose() {
      axios.delete(`/api/admin/events/${this.eventId}/teams/${this.teamId}/choose`)
        .then(_ => {
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
    },
    saveTaskScore(score) {
      const data = {
        task: {
          id: score.task.id
        },
        score: score.scoreDelta
      }
      axios.post(`/api/admin/events/${this.eventId}/teams/${this.teamId}/score`, data)
        .then(response => {
          this.team.scores = response.data;
        })
    }
  }
}
</script>

<style scoped>

</style>
