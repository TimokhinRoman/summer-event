<template>
  <p class="text-3xl font-bold">Команды</p>
  <div v-if="teams && teams.length > 0" class="w-full">
    <ul class="list-none p-0">
      <li v-for="team in teams" :key="team.id" class="text-xl my-2">
        {{ team.name }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TeamListView",
  data() {
    return {
      teams: null
    }
  },
  created() {
    this.loadTeams();
  },
  methods: {
    loadTeams() {
      axios.get("/api/admin/teams")
        .then(response => {
          console.log(response);
          this.teams = response.data;
        })
    }
  }
}
</script>

<style scoped>

</style>
