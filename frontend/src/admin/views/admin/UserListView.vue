<template>
  <p class="text-3xl font-bold">Пользователи</p>
  <div v-if="users && users.length > 0" class="w-full">
    <ul class="list-none p-0">
      <li v-for="user in users" :key="user.id" class="text-xl my-2">
        {{ user.name }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserListView",
  data() {
    return {
      users: null
    }
  },
  created() {
    this.loadUsers();
  },
  methods: {
    loadUsers() {
      axios.get("/api/admin/users")
        .then(response => {
          console.log(response);
          this.users = response.data;
        })
    }
  }
}
</script>

<style scoped>

</style>
