<template>
  <form @submit.prevent="saveEvent">
    <div class="card box">
      <div class="card-body">
        <h5 class="card-title mb-2">Добавление события:</h5>
        <input-text-field v-model="event.name" name="name" label="Название" placeholder="Название"/>
        <input-text-area-field v-model="event.description" name="description" label="Описание" placeholder="Описание"/>

        <div>
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
  name: "EventAddView",
  components: {
    InputTextField, InputTextAreaField
  },
  data() {
    return {
      event: {
        name: null,
        description: null
      }
    }
  },
  methods: {
    saveEvent() {
      console.log("Saving event...", this.event);
      axios.post("/api/admin/events", this.event)
        .then(response => {
          console.log(response);
          router.push({
            name: "Event", params: {
              eventId: response.data.id
            }
          })
        })
    }
  }
}
</script>
