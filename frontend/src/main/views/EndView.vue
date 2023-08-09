<template>
  <div class="background"/>
  <div class="container">
    <div class="content p-fluid">
      <template v-if="loading">
        <span class="text-2xl">загрузка...</span>
      </template>
      <template v-else>
        <p class="text-2xl shadow-outline">Таблица результатов</p>
        <table class="w-full score-table">
          <thead>
          <tr>
            <td></td>
            <td v-for="team in teams" :key="team.id">
              <span class="shadow-outline">{{ team.name }}</span>
            </td>
          </tr>
          </thead>
          <tbody>
          <tr v-for="task in tasks" :key="task.id">
            <td class="text-left">
              <span class="shadow-outline">{{ task.name }}</span>
            </td>
            <td v-for="team in teams" :key="team.id">
              <span class="shadow-outline">{{ getScore(task, team) }}</span>
            </td>
          </tr>
          </tbody>
        </table>
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EndView",
  data() {
    return {
      tasks: null,
      teams: null,
      scores: null,
      loading: true
    }
  },
  created() {
    this.loadScores();
  },
  methods: {
    loadScores() {
      this.loading = true;
      axios.get("/api/scores")
        .then(response => {
          this.tasks = response.data.tasks;
          this.teams = response.data.teams;
          this.scores = response.data.scores;
          this.loading = false;
        });
    },
    getScore(task, team) {
      return this.scores[task.id]?.[team.id];
    }
  }
}
</script>

<style>
.score-table {
  border-collapse: collapse;
}

.score-table, .score-table th, .score-table td {
  border: 1px solid;
}
</style>
