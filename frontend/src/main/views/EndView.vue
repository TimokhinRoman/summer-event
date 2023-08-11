<template>
  <div class="background"/>
  <div class="container end-view">
    <div class="content p-fluid">
      <template v-if="loading">
        <span class="text-2xl">загрузка...</span>
      </template>
      <template v-else>
        <Panel header="Таблица результатов">
          <table class="w-full score-table">
            <thead>
            <tr>
              <td></td>
              <td v-for="team in teams" :key="team.id">
                <div class="team-name">
                  <span>{{ getTeamName(team) }}</span>
                </div>
              </td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="task in tasks" :key="task.id">
              <td class="text-left">
                <span>{{ task.name }}</span>
              </td>
              <td v-for="team in teams" :key="team.id">
                <span>{{ getScore(task, team) }}</span>
              </td>
            </tr>
            <tr class="font-bold">
              <td class="text-left">
                <span>Итог</span>
              </td>
              <td v-for="team in teams" :key="team.id">
                <span>{{ getTotalScore(team) }}</span>
              </td>
            </tr>
            </tbody>
          </table>
        </Panel>
      </template>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Panel from "primevue/panel";

export default {
  name: "EndView",
  components: {Panel},
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
    },
    getTotalScore(team) {
      return Object.values(this.scores)
        .map(teamScores => teamScores[team.id])
        .filter(score => Number.isInteger(score))
        .reduce((total, score) => total + score, 0);
    },
    getTeamName(team) {
      return team.name;
      //return team.name.substring(0, 3);
    }
  }
}
</script>

<style>
.score-table {
  border-collapse: collapse;
}

.score-table th, .score-table td {
  border: 1px solid #3f4b5b;
}

.score-table td:first-child {
  border-left: none
}

.score-table td:last-child {
  border-right: none;
}

.score-table td {
  padding-top: 4px;
  padding-bottom: 4px;
}

.score-table thead tr:first-child td {
  border-top: none;
}

.score-table tbody tr:last-child td {
  border-bottom: none;
}

.end-view .p-panel-header {
  justify-content: center !important;
  border-bottom: none !important;
}

.end-view .p-panel-title {
  font-size: 1.25rem !important;
}

.end-view .p-panel-header, .p-panel-content {
  background-color: rgba(63, 75, 91, 0.8) !important;
}

.end-view .p-panel-content {
  padding-top: 0 !important;
}

.end-view .team-name {
  width: 50px;
  padding-left: 2px;
  padding-right: 2px;
  word-break: break-all;
}

.end-view span {
  color: white;
}
</style>
