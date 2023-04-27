<template>
  <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px; height: 100vh">
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <nav-link :to="{name: 'Admin'}" label="Домой"/>
      </li>
      <li>
        <nav-link :to="{name: 'EventList'}" label="События"/>
      </li>
    </ul>
  </div>
  <div class="p-4">
    <router-view/>
  </div>
</template>

<script>
// @ is an alias to /src

import NavLink from "@/components/admin/NavLink";

export default {
  name: 'AdminView',
  components: {NavLink},
}
</script>
