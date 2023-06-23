<template>
  <div class="full-screen">
    <div class="container p-fluid">
      <template v-if="loading">
        <p class="m-auto">загрузка...</p>
      </template>
      <template v-else-if="selectedTeam">
        <div class="content">
          <div class="text-xl">Твоя команда</div>
          <p class="selected-team-name">{{ selectedTeam.name }}</p>
          <span class="text-xl">Участники</span>
          <div class="selected-team-user-list">
            <div class="selected-team-user" v-for="user in selectedTeam.users" :key="user.id">
              {{ user.name }}
            </div>
          </div>
        </div>
        <div>
          ожидайте начала...
        </div>
        <div class="mt-4 mb-6">
          <Button type="button" label="Покинуть команду" class="text-lg font-medium" @click="leaveTeam"/>
        </div>
      </template>
      <template v-else>
        <div class="content">
          <div>
            <p class="text-3xl">Привет, <b>{{ user.name }}</b>!</p>
            <p>введи название своей команды</p>
          </div>
          <Form ref="form" @submit="onSubmit" :validation-schema="schema">
            <div class="form-container mt-2">
              <input-text-field type="text" name="name" label="Название команды"/>
            </div>
            <div class="button-container mt-3">
              <Button type="submit" label="Присоединиться" class="text-xl font-medium"/>
            </div>
          </Form>
          <div class="team-list" v-if="teams && teams.length > 0">
            <p>или выбери одну из списка</p>
            <template v-for="team in teams" :key="team.id">
              <Button :label="team.name" plain text class="text-2xl text-white font-medium" @click="joinTeam({id: team.id})"/>
            </template>
          </div>
        </div>
      </template>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {object, string} from 'yup';
import axios from 'axios';

import {Form} from 'vee-validate';
import InputTextField from '@/admin/components/InputTextField';
import Button from 'primevue/button';

const loading = ref(true);
const user = ref(null);
const selectedTeam = ref(null);
const teams = ref([]);

const schema = object({
  name: string()
    .required("название команды обязательно для заполнения")
    .max(255, "название команды не должно превышать 255 символов"),
})

function onSubmit(values) {
  joinTeam(values);
}

function loadLobby() {
  loading.value = true;
  axios.get("/api/lobby")
    .then(response => {
      console.log(response.data);
      user.value = response.data.user;
      selectedTeam.value = response.data.selectedTeam;
      teams.value = response.data.teams;
      loading.value = false;
    });
}

function joinTeam(team) {
  console.log(team);
  axios.post("/api/team/join", team).then(response => {
    console.log(response);
    selectedTeam.value = response.data;
  })
}

function leaveTeam() {
  axios.delete("/api/team/leave").then(() => {
    loadLobby();
  })
}

onMounted(() => {
  loadLobby();
})
</script>

<style scoped>
@import "../../assets/app.css";

form {
  width: 300px
}

.team-list {
  margin-top: 20px;
}

.selected-team-name {
  font-size: 1.75rem;
  font-weight: 700;
}

.selected-team-user-list {

}

.selected-team-user {
  margin: 10px 0;
  font-weight: 500;
  font-size: 1.5rem;
}
</style>
