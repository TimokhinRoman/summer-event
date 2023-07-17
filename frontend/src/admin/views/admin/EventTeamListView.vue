<template>
  <div class="content">
    <p class="text-3xl font-bold">Команды</p>
    <template v-if="teams && teams.length > 0">
      <ul class="list-none p-0 w-full">
        <li v-for="team in teams" :key="team.id" class="my-2">
          <router-link :to="teamLink(team)" class="no-underline text-white">
            <span class="text-xl">{{ team.name }}</span>
          </router-link>
          <font-awesome-icon v-if="team.chooser" icon="fa-solid fa-dice" size="xl" class="ml-2 vertical-align-sub"/>
        </li>
      </ul>
    </template>
    <div v-else>пусто</div>
  </div>
  <div class="w-full">
    <Button label="Назад" @click="back"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventTeamListView",
  data() {
    return {
      teams: null
    }
  },
  computed: {
    eventId() {
      return this.$route.params.eventId;
    }
  },
  created() {
    this.loadTeams();
  },
  methods: {
    loadTeams() {
      axios.get(`/api/admin/events/${this.eventId}/teams`)
        .then(response => {
          console.log(response);
          this.teams = response.data;
        })
    },
    teamLink(team) {
      return {
        name: "EventTeam",
        params: {
          eventId: this.eventId,
          teamId: team.id
        }
      }
    },
    back() {
      this.$router.push({
        name: "Event",
        params: {
          eventId: this.eventId
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
