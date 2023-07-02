<script setup>
import Dock from "primevue/dock"
</script>

<template>
  <div class="p-dock p-component p-dock-top pointer-events-auto">
    <div class="p-2">
        <span class="p-buttonset">
            <Button label="События" @click="$router.push('/events')">
              <font-awesome-icon icon="fa-solid fa-calendar-days" size="xl"/>
            </Button>
            <Button label="Команды" @click="$router.push('/teams')">
              <font-awesome-icon icon="fa-solid fa-users-line" size="xl"/>
            </Button>
            <Button label="Юзеры" @click="$router.push('/users')">
              <font-awesome-icon icon="fa-solid fa-user" size="xl"/>
            </Button>
        </span>
    </div>
  </div>
  <div class="content p-fluid">
    <router-view/>
  </div>

  <!--div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px; height: 100vh">
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <nav-link :to="{name: 'Admin'}" label="Домой"/>
      </li>
      <li>
        <nav-link :to="{name: 'EventList'}" label="События"/>
      </li>
    </ul>
  </div-->
  <!--div class="p-4">
    <router-view/>
  </div-->
</template>

<script>

export default {
  name: 'AdminView',
}
</script>
