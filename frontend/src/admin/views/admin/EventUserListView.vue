<template>
  <div class="content">
    <p class="text-3xl font-bold">Участники</p>
    <template v-if="users && users.length > 0">
      <ul class="list-none p-0">
        <li v-for="user in users" :key="user.id" class="text-xl my-2">
          {{ user.name }}
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
  name: "EventUserListView",
  data() {
    return {
      users: null
    }
  },
  computed: {
    eventId() {
      return this.$route.params.eventId;
    }
  },
  created() {
    this.loadUsers();
  },
  methods: {
    loadUsers() {
      axios.get(`/api/admin/events/${this.eventId}/users`)
        .then(response => {
          console.log(response);
          this.users = response.data;
        })
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
