<template>
  <router-link :to=to class="nav-link text-white" exact-active-class="active">{{label}}</router-link>
</template>

<script>
export default {
  name: "NavLink",
  props: ["to", "label"]
}
</script>
