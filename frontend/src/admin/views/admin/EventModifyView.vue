<template>
  <form @submit.prevent="saveEvent">
    <div class="card box">
      <div class="card-body">
        <h5 class="card-title mb-2">Редактирование события:</h5>
        <input-text-field v-model="event.name" name="name" label="Название" placeholder="Название"/>
        <input-text-area-field v-model="event.description" name="description" label="Описание" placeholder="Описание"/>

        <div>
          <router-link :to="{name: 'Event', params: event.id}" class="btn btn-primary me-2"
                       role="button">Назад
          </router-link>
          <button class="btn btn-success">Сохранить</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>

import InputTextField from "@/admin/components/InputTextField";
import InputTextAreaField from "@/admin/components/InputTextAreaField";
import axios from "axios";
import router from "@/admin/router";

export default {
  name: "EventModifyView",
  components: {
    InputTextField, InputTextAreaField
  },
  data() {
    return {
      event: {
        id: this.$route.params.eventId,
        name: null,
        description: null
      }
    }
  },
  created() {
    this.loadEvent();
  },
  methods: {
    loadEvent() {
      axios.get("/api/admin/events/" + this.event.id)
        .then(response => {
          console.log(response);
          this.event = response.data;
        })
    },
    saveEvent() {
      console.log("Saving event...", this.event);
      axios.post("/api/admin/events/" + this.event.id, this.event)
        .then(response => {
          console.log(response);
          router.push({name: "Event", params: this.event.id})
        })
    }
  }
}
</script>
