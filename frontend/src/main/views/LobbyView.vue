<template>
  <div class="background"/>
  <div class="container">
    <div class="content p-fluid">
      <template v-if="loading">
        <p class="m-auto shadow-outline text-2xl">загрузка...</p>
      </template>
      <template v-else-if="selectedTeam">
        <div class="content">
          <span class="text-xl shadow-outline">Твоя команда</span>
          <p class="selected-team-name shadow-outline">{{ selectedTeam.name }}</p>
          <span class="text-xl shadow-outline">Участники</span>
          <div>
            <div class="my-1" v-for="user in selectedTeam.users" :key="user.id">
              <span class="font-medium text-2xl shadow-outline">{{ user.name }}</span>
              <font-awesome-icon v-if="isCaptain(user)" icon="fa-solid fa-crown" size="xl"
                                 class="ml-2 vertical-align-sub"/>
            </div>
          </div>
        </div>
        <div>
          <span class="shadow-outline">ожидайте начала...</span>
        </div>
        <div class="w-full mt-4">
          <Button type="button" label="Покинуть команду" class="text-lg font-medium" @click="leaveTeam"/>
        </div>
      </template>
      <template v-else>
        <div>
          <p class="text-3xl shadow-outline">Привет, <b>{{ user.name }}</b>!</p>
          <p class="shadow-outline">введи название своей команды</p>
        </div>
        <Form ref="form" @submit="onSubmit" :validation-schema="schema" class="w-full">
          <div class="form-container mt-2">
            <input-text-field type="text" name="name" label="Название команды"/>
          </div>
          <div class="button-container mt-3">
            <Button type="submit" label="Присоединиться" class="text-xl font-medium"/>
          </div>
        </Form>
        <div class="team-list" v-if="teams && teams.length > 0">
          <p class="shadow-outline">или выбери одну из списка</p>
          <template v-for="team in teams" :key="team.id">
            <Button :label="team.name" plain text class="text-2xl text-white font-medium shadow-outline"
                    @click="joinTeam({id: team.id})"/>
          </template>
        </div>
      </template>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import {object, string} from 'yup';
import axios from 'axios';
import router from "@/main/router";

import {Form} from 'vee-validate';
import InputTextField from '@/admin/components/InputTextField';

const loading = ref(true);
const user = ref(null);
const selectedTeam = ref(null);
const teams = ref([]);
const waitingForStart = ref(null);

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

      if (selectedTeam.value) {
        waitForStart();
      }
    });
}

function joinTeam(team) {
  console.log(team);
  axios.post("/api/team/join", team).then(response => {
    console.log(response);
    selectedTeam.value = response.data;

    waitForStart();
  })
}

function leaveTeam() {
  cancelWaitingForStart();
  axios.delete("/api/team/leave").then(() => {
    loadLobby();
  })
}

function isCaptain(user) {
  const team = selectedTeam.value;
  return team && team.captain && team.captain.id === user.id;
}

function waitForStart() {
  waitingForStart.value = setInterval(() => {
    axios.get("/api/event").then(response => {
      let event = response.data;
      if (event) {
        switch (event.status) {
          case "PENDING":
            return;
          case "DRAW":
            router.push("/draw");
            break;
          default:
            router.push("/");
            break;
        }
      } else {
        router.push("/");
      }
      cancelWaitingForStart();
    })
  }, 2000)
}

function cancelWaitingForStart() {
  if (waitingForStart.value) {
    clearInterval(waitingForStart.value);
    waitingForStart.value = null;
  }
}

onMounted(() => {
  loadLobby();
})
</script>

<style scoped>
.team-list {
  margin-top: 20px;
}

.selected-team-name {
  font-size: 1.75rem;
  font-weight: 700;
}
</style>
